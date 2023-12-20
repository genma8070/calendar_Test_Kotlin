package com.example.holiday.entity;

import java.time.LocalDate;

public class Event {
	
	private String type;
	
	private LocalDate date;
	
	private String content;

	public Event(String type, LocalDate date, String content) {
		super();
		this.type = type;
		this.date = date;
		this.content = content;
	}

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
