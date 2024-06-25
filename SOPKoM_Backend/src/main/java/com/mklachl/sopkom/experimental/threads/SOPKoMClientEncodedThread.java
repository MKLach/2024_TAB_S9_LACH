package com.mklachl.sopkom.experimental.threads;

import java.io.DataInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mklachl.network.Client;
import com.mklachl.network.Server;
import com.mklachl.network.handlers.threads.ClientServiceThread;
import com.mklachl.network.packet.system.InformationPacket;
import com.mklachl.network.security.ClientPrivileges;
import com.mklachl.network.security.SimpleUser;
import com.mklachl.network.security.cryptography.CryptographyUtil;
import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.repository.UserRepository;

public class SOPKoMClientEncodedThread extends ClientServiceThread{

	
	
	UserRepository userRepository;
	
	PasswordEncoder passwordEncoder;
	
	public SOPKoMClientEncodedThread(UserRepository userRepository, PasswordEncoder passwordEncoder, Server server, long milisTimeout, long nanosTimeout) {
		super(server, milisTimeout, nanosTimeout);
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	
	@Override
	public boolean init() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] username = handler.readClientData(client);
		
		CryptographyUtil util = new CryptographyUtil("qwertyqwertyqwer");
		
		byte[] visibleUsername = handler.readClientData(client);
		
		DataInputStream stream = new DataInputStream(client.getInputStream());
		
		int privilieges = stream.readInt();
		
		String usernameDE = new String(visibleUsername);
	
		String password = util.decrypt(username);
		
		client.setVisibleUsername(usernameDE);
		
		SimpleUser user = null;
		User userOp = userRepository.findByLogin(usernameDE);
		
	
		
		try {
	
			if(userOp == null) {
				
				disconnect("Username data is wrong.");
				
				return false;
			}
			
			if(! passwordEncoder.matches(password, userOp.getPassword())) {
				disconnect("Password data is wrong.");
				
				return false;
			}
			
			user = new SimpleUser(usernameDE, ClientPrivileges.getFromL(privilieges));
		
			client.setUser(user);
		
			if(handler.getConnectedClients().containsValue(user)) {
				
				if(!user.getPrivileges().equals(ClientPrivileges.UNKWOWN)) {
					
					disconnect("User already logged in.");
					
					return false;
				}

			}
			
			
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		} 
		
		String userVisibleNameKey = usernameDE;
		
		
		if(userVisibleNameKey.length() < 16) {
			
			while(userVisibleNameKey.length() != 16) {
				
				userVisibleNameKey += userVisibleNameKey.charAt(0);
				
			}
			
			
		} else if (userVisibleNameKey.length() > 16) {
			
			userVisibleNameKey = userVisibleNameKey.substring(0, 16);
			
		}
		
		CryptographyUtil temp = new CryptographyUtil(userVisibleNameKey);
	
	
		handler.addReadyClient(client, user);
		
		handler.sendToClient(new InformationPacket("User Accepted."), client);
		
		handler.writeData(client, temp.encrypt(handler.getCrypt().getKey()));
		
		
	
		this.setName("Service thread for user: " + client.getVisibleName());
		
		return true;
		
	}
}
