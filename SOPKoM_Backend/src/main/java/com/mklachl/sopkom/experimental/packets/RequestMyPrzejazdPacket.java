package com.mklachl.sopkom.experimental.packets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.repository.PrzejazdRepository;
import com.mklachl.sopkom.repository.UserRepository;

public class RequestMyPrzejazdPacket implements IPacket{

	PrzejazdRepository repo = SOPKoMBusServerHandler.getInstance().przejazdyRepository;
	
	
	UserRepository userrepo = SOPKoMBusServerHandler.getInstance().userRepository;
	 
	
	KierowcaRepository kierowcaRepo = SOPKoMBusServerHandler.getInstance().kierowcaRepository;
	
	public RequestMyPrzejazdPacket() {
		
	}
	
	long id;
	
	public RequestMyPrzejazdPacket(long l) {
		this.id = l;
	}
	
	@Override
	public void writeToBuffer(ByteBufferUtil buffer) {
		buffer.writeLong(id);
	}

	@Override
	public void readFromBuffer(ByteBufferUtil buffer) {
		id = buffer.readLong();
	}

	@Override
	public IPacket executePacket(PacketContext context) {
		try {
			
			User userIn = userrepo.findByLogin(context.asClientToServerContext().getSender().getVisibleName());
			
			Kierowca kierowca = kierowcaRepo.findByUser(userIn);
			
			var przejazd = repo.findById(id);
			
			if(przejazd.isEmpty()) {
				
				context.asClientToServerContext().getServer().sendToClientDataStream(new InformationPacket("404"), context.asClientToServerContext().getSender());
				return null;
			}
			
			if(przejazd.get().getKierowca().getKierowcaId().longValue() != kierowca.getKierowcaId().longValue()) {
				
				context.asClientToServerContext().getServer().sendToClientDataStream(new InformationPacket("To nie twoj przejazd!"), context.asClientToServerContext().getSender());
				return null;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			String outData;
			
			var out = new PrzejazdDtoOutput(przejazd.get());
			
			outData = mapper.writeValueAsString(out);
			
			outData = PolishLettersReplacer.replacePolishLetters(outData);
			
			context.asClientToServerContext().getServer().sendToClientDataStream(new MyPrzejazdUpdatePacket(outData), context.asClientToServerContext().getSender());
			
		} catch (IOException | InvalidSidedContextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean useEncryption() {
		
		return false;
	}

}
