package net.skhu.etc;

import lombok.Data;

@Data
public class Message {
	String message;
	
	public Message(String message) {
		this.message = message;
	}
}
