package com.mklachl.sopkom.experimental.packets;

import com.mklachl.network.packet.IPacket;
import com.mklachl.network.packet.PacketContext;
import com.mklachl.network.packet.buffer.ByteBufferUtil;
import com.mklachl.sopkom.model.entity.Przejazd;

public class MyPrzejazdyPacket implements IPacket {

	public MyPrzejazdyPacket() {
		
	}
	String data;
	
	public MyPrzejazdyPacket(String data) {
		this.data = data;
	}

	@Override
	public void writeToBuffer(ByteBufferUtil buffer) {
		buffer.writeString(data);
	}

	@Override
	public void readFromBuffer(ByteBufferUtil buffer) {
		
	}

	@Override
	public IPacket executePacket(PacketContext context) {
		
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
