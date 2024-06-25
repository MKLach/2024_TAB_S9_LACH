package com.mklachl.sopkom.experimental.packets;

import java.io.IOException;

import com.mklachl.network.exceptions.InvalidSidedContextException;
import com.mklachl.network.packet.IPacket;
import com.mklachl.network.packet.PacketContext;
import com.mklachl.network.packet.buffer.ByteBufferUtil;

public class DisconnectPacket implements IPacket {

	public DisconnectPacket() {
		
	}
	
	String reason;

	public DisconnectPacket(String reason) {
		this.reason = reason;
	}

	
	@Override
	public void writeToBuffer(ByteBufferUtil buffer) {
		buffer.writeString(reason);
	}

	@Override
	public void readFromBuffer(ByteBufferUtil buffer) {
		reason = buffer.readString();
	}

	@Override
	public IPacket executePacket(PacketContext context) {
		
		System.out.println("Client is disconecting: " + reason);
		
		try {
			context.asClientToServerContext().getServer().getConnectedClients().remove(
					context.asClientToServerContext().getSender()
			);
			
			context.asClientToServerContext().getServer().disconnect(context.asClientToServerContext().getSender(), reason);
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (InvalidSidedContextException e) {
			
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public boolean useEncryption() {
		
		return false;
	}

}
