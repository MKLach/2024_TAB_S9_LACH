package com.mklachl.sopkom.model.dto;

public class Message {

	public String message;
	
	public Message() {
		
	}

	public Message(String message) {
		this.message = message;
	}
	
	public Message SetMessageInt(String message) {
		this.message = message;
		return this;
	}
	
	public static Message SetMessage(String message) {
		return new Message().SetMessageInt(message);
	}
	
}
