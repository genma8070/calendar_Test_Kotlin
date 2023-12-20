package com.example.holiday.vo.response;

import java.util.List;

import com.example.holiday.entity.Event;
import com.fasterxml.jackson.annotation.JsonInclude;

//ニュース関連のリスポンスのクラス
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HolidayResponse {
	
	private Integer id;

//	ニュースのメインカテゴリ
	private String year;

//	ニュースのサブカテゴリ
	private String month;

//	ニュースのタイトル
	private String day;
	
	private List<Event> event;

//	ニュースのサブタイトル
	private String holidayName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public HolidayResponse(Integer id, String year, String month, String day, String holidayName) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.day = day;
		this.holidayName = holidayName;
	}

	public HolidayResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

	public HolidayResponse(List<Event> event) {
		super();
		this.event = event;
	}
	
}
