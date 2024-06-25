package com.mklachl.sopkom.experimental.packets;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mklachl.network.exceptions.InvalidSidedContextException;
import com.mklachl.network.packet.IPacket;
import com.mklachl.network.packet.PacketContext;
import com.mklachl.network.packet.buffer.ByteBufferUtil;
import com.mklachl.network.packet.system.InformationPacket;
import com.mklachl.sopkom.experimental.SOPKoMBusServerHandler;
import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoUpdate;
import com.mklachl.sopkom.services.PrzejazdService;

public class DOSERWERA_UPDATE_PACKET implements IPacket {

	PrzejazdService service = SOPKoMBusServerHandler.getInstance().przejazdService;
	
	public DOSERWERA_UPDATE_PACKET() {
		
	}
	String data;
	
	public DOSERWERA_UPDATE_PACKET(String data) {
		this.data = data;
	}

	@Override
	public void writeToBuffer(ByteBufferUtil buffer) {
		buffer.writeString(data);
	}

	@Override
	public void readFromBuffer(ByteBufferUtil buffer) {
		data = buffer.readString();
	}

	@Override
	public IPacket executePacket(PacketContext context) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			PrzejazdDtoUpdate upd = mapper.readValue(data, PrzejazdDtoUpdate.class);
		
			System.out.println(upd);
		
		
			try {
				var ret = this.service.updatePrzejazd(upd);
				
				
			} catch (Exception e) {
				
				try {
					context.asClientToServerContext().getServer().sendToClientDataStream(new InformationPacket("blad danych!"), context.asClientToServerContext().getSender());
				} catch (IOException | InvalidSidedContextException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				e.printStackTrace();
			}
		
			try {
				context.asClientToServerContext().getServer().sendToClientDataStream(new InformationPacket("Przejazd zapisany!"), context.asClientToServerContext().getSender());
			} catch (IOException | InvalidSidedContextException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
		
		
		
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public boolean useEncryption() {
		
		return false;
	}

	@Override
	public int getBufferSize() {
		
		return IPacket.super.getBufferSize() + 20 * 1025;
	}
}
