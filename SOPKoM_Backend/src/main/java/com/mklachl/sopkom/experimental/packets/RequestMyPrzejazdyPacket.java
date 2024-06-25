package com.mklachl.sopkom.experimental.packets;

import java.io.IOException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.spring6.context.SpringContextUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mklachl.network.exceptions.InvalidSidedContextException;
import com.mklachl.network.packet.IPacket;
import com.mklachl.network.packet.PacketContext;
import com.mklachl.network.packet.buffer.ByteBufferUtil;
import com.mklachl.network.packet.system.InformationPacket;
import com.mklachl.sopkom.experimental.PolishLettersReplacer;
import com.mklachl.sopkom.experimental.SOPKoMBusServerHandler;
import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoOutput;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.repository.PrzejazdRepository;
import com.mklachl.sopkom.repository.UserRepository;

public class RequestMyPrzejazdyPacket implements IPacket{

	
	PrzejazdRepository repo = SOPKoMBusServerHandler.getInstance().przejazdyRepository;
	
	
	UserRepository userrepo = SOPKoMBusServerHandler.getInstance().userRepository;
	 
	
	KierowcaRepository kierowcaRepo = SOPKoMBusServerHandler.getInstance().kierowcaRepository;
	
	
	
	public RequestMyPrzejazdyPacket() {
		
	}
	
	@Override
	public void writeToBuffer(ByteBufferUtil buffer) {
		
	}

	@Override
	public void readFromBuffer(ByteBufferUtil buffer) {
		
	}

	@Override
	public IPacket executePacket(PacketContext context) {
		
		try {
			
			User userIn = userrepo.findByLogin(context.asClientToServerContext().getSender().getVisibleName());
			
			Kierowca kierowca = kierowcaRepo.findByUser(userIn);
			
			var przejazdy = repo.findAllByKierowca(kierowca);
			
			var out = przejazdy.stream().map(PrzejazdDtoOutput::new).collect(Collectors.toList());
			
			ObjectMapper mapper = new ObjectMapper();
			String outData;
			
			outData = mapper.writeValueAsString(out);
			
			outData = PolishLettersReplacer.replacePolishLetters(outData);
			System.out.println("len " +outData.length());
			context.asClientToServerContext().getServer().sendToClientDataStream(new MyPrzejazdyPacket(outData), context.asClientToServerContext().getSender());
			
		} catch (InvalidSidedContextException | JsonProcessingException e) {
			
			e.printStackTrace();
			return new InformationPacket("internal server errror: "+ e.getMessage());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return null;
		
		
	}

	@Override
	public boolean useEncryption() {
		
		return false;
	}

}
