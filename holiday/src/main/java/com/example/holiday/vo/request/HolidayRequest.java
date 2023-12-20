package com.example.holiday.vo.request;

//ニュース関連のリクエスト
public class HolidayRequest {
	
	private Integer id;

//	ニュースのメインカテゴリ
	private String year;

//	ニュースのサブカテゴリ
	private String month;

//	ニュースのタイトル
	private String day;

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
	
}
