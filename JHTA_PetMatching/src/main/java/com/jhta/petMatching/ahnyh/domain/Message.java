package com.jhta.petMatching.ahnyh.domain;

public class Message {
	private int message_num;
	private String sender_id;
	private String receiver_id;
	private String message_title;
	private String message_content;
	private String deleteby_sender;
	private String deleteby_receiver;
	private String cancelby_sender;
	private int read_count;
	private String register_date;
	
	
	public int getMessage_num() {
		return message_num;
	}
	public void setMessage_num(int message_num) {
		this.message_num = message_num;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public String getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getMessage_title() {
		return message_title;
	}
	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public String getDeleteby_sender() {
		return deleteby_sender;
	}
	public void setDeleteby_sender(String deleteby_sender) {
		this.deleteby_sender = deleteby_sender;
	}
	public String getDeleteby_receiver() {
		return deleteby_receiver;
	}
	public void setDeleteby_receiver(String deleteby_receiver) {
		this.deleteby_receiver = deleteby_receiver;
	}
	public String getCancelby_sender() {
		return cancelby_sender;
	}
	public void setCancelby_sender(String cancelby_sender) {
		this.cancelby_sender = cancelby_sender;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	
	
	
	
	
	
	
	
}
