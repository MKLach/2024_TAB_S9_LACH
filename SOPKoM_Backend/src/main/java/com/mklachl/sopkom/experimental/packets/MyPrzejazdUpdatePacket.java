package com.mklachl.sopkom.experimental.packets;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mklachl.network.packet.IPacket;
import com.mklachl.network.packet.PacketContext;
import com.mklachl.network.packet.buffer.ByteBufferUtil;
import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoOutput;

public class MyPrzejazdUpdatePacket implements IPacket {

	public MyPrzejazdUpdatePacket() {
		
	}
	String data;
	
	public MyPrzejazdUpdatePacket(String data) {
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
		//System.out.println(data);
		//System.out.println(data.length());
		
		try {
			PrzejazdDtoOutput in = mapper.readValue(data, PrzejazdDtoOutput.class);
			
			//System.out.println(dataIn.size());
			SimpleDateFormat date = new SimpleDateFormat("HH:mm, dd.MM.yyyy");
			//date.setTimeZone(TimeZone.getTimeZone("CEST"));
	        
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
	        dateFormat.setTimeZone(TimeZone.getTimeZone("CEST"));

	        SimpleDateFormat dateFormat2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
	        dateFormat2.setTimeZone(TimeZone.getTimeZone("CEST"));
	        
	        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
	        time.setTimeZone(TimeZone.getTimeZone("CEST"));
	        
			System.out.println("Id: "+ in.getPrzejazdId() + " " + in.getLiniaNumer()+ " "+date.format(in.getDataStartu()) + " " + in.getStatus() + ", ilosc przystankow: " + in.getPrzystanki().size());
		    in.getPrzystanki().sort((a,b) -> {
					
				if(in.getKierunek() == 0) {
					return a.getKolejnosc() - b.getKolejnosc();
				} else {
					return b.getKolejnosc() - a.getKolejnosc();
				}
					
			});
		    
		    
		    
		    
			for(var temp : in.getPrzystanki()) {
				System.out.println("\t" + temp.getNazwa() + ", " + time.format(temp.getPrzewidywanaGodzinna()) + " | " + (temp.getRealnaGodzinna() != null ? date.format(temp.getRealnaGodzinna()) : "--:--, --.--.----"));
				
			}
			System.out.println();
			
			
			
			
			
		
		
			
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
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
