package com.iiplabs.wsc.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iiplabs.wsc.code.MessageType;

@SuppressWarnings("serial")
public abstract class BaseMessageDto implements Serializable {

	@JsonProperty("sequence_id")
	private UUID sequenceId;
	@JsonProperty("type")
	private MessageType messageType;
	
	public BaseMessageDto() {
		sequenceId = UUID.randomUUID();
	}
	
	public UUID getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(UUID sequenceId) {
		this.sequenceId = sequenceId;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
}
