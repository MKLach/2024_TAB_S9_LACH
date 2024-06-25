package com.mklachl.sopkom.experimental;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mklachl.network.Server;
import com.mklachl.network.exceptions.InvalidUserException;
import com.mklachl.network.handlers.AbstractServerHandler;
import com.mklachl.network.handlers.interfaces.IServerHandler;
import com.mklachl.network.security.ClientPrivileges;
import com.mklachl.network.security.cryptography.CryptographyUtil;
import com.mklachl.sopkom.experimental.packets.DOSERWERA_UPDATE_PACKET;
import com.mklachl.sopkom.experimental.packets.DisconnectPacket;
import com.mklachl.sopkom.experimental.packets.MyPrzejazdUpdatePacket;
import com.mklachl.sopkom.experimental.packets.MyPrzejazdyPacket;
import com.mklachl.sopkom.experimental.packets.RequestMyPrzejazdPacket;
import com.mklachl.sopkom.experimental.packets.RequestMyPrzejazdyPacket;
import com.mklachl.sopkom.experimental.threads.SOPKoMClientEncodedThread;
import com.mklachl.sopkom.model.entity.Role;
import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.repository.PrzejazdRepository;
import com.mklachl.sopkom.repository.UserRepository;
import com.mklachl.sopkom.services.KierowcaService;
import com.mklachl.sopkom.services.PrzejazdService;
import com.mklachl.sopkom.services.UserService;

import jakarta.annotation.PostConstruct;

@Service
public class SOPKoMBusServerHandler extends AbstractServerHandler implements IServerHandler {
	
	Logger logger = LoggerFactory.getLogger(SOPKoMBusServerHandler.class);
	
	public static final File DIRECTORY = new File("BusHandlingServer");
	
	@Autowired
	public
	PrzejazdService przejazdService;

	@Autowired
	public
	KierowcaRepository kierowcaRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	public
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder en;

	@Autowired
	public
	PrzejazdRepository przejazdyRepository;

	@Override
	public void init() {
		System.out.println(((BCryptPasswordEncoder)en));
		
		kierowcaRepository.findAll().forEach((kierowca) ->{
			BCryptPasswordEncoder e;
			
			if(kierowca.getUser() == null) {
				
				User user = new User();
				user.setLogin((kierowca.getImie()+"."+kierowca.getNazwisko()).toLowerCase());
				user.setPassword(en.encode("qwerqwerqwerqwer"));
				
				Set<Role> roles = new HashSet<>();
				roles.add(Role.DRIVER);
				user.setRoles(roles);
				user.setName(user.getLogin());
				
				user = userRepository.save(user);
				
				kierowca.setUser(user);
				kierowca = kierowcaRepository.save(kierowca);
				
				
			}
			
		});
	
	}

	@Override
	public void loop() {
		while(isRunning()) {
			
			try {
				this.getServerInstance().acceptClient(new SOPKoMClientEncodedThread(userRepository, en, server, 100, 0));
				
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | IOException | InvalidUserException e) {
				
				e.printStackTrace();
			}
			
		}
	}


	@Override
	public void registerMsgs(Server serverIn) {
		serverIn.register(DisconnectPacket.class, ClientPrivileges.UNKWOWN);
		serverIn.register(MyPrzejazdyPacket.class, ClientPrivileges.UNKWOWN);
		serverIn.register(MyPrzejazdUpdatePacket.class, ClientPrivileges.UNKWOWN);
		serverIn.register(RequestMyPrzejazdyPacket.class, ClientPrivileges.UNKWOWN);
		serverIn.register(RequestMyPrzejazdPacket.class, ClientPrivileges.UNKWOWN);
		serverIn.register(DOSERWERA_UPDATE_PACKET.class, ClientPrivileges.UNKWOWN);
	}

	@PostConstruct
	public void runServer() {
		
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				logger.info("mklachl server handler started!");
				
				try {
					SOPKoMBusServerHandler.this.initServer(60053, DIRECTORY, new CryptographyUtil("1234123412341234"));
				
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				SOPKoMBusServerHandler.this.init();
				SOPKoMBusServerHandler.this.loop();
				
			}
		}).start();
		
	}
	
	public static SOPKoMBusServerHandler getInstance() {
		
		return (SOPKoMBusServerHandler)handler;
	}
	
}
