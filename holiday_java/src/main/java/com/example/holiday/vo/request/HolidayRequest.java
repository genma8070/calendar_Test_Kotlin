package com.example.holiday.vo.request;

//祝日関連のリクエスト
public class HolidayRequest {
	
//	祝日ID	
	private Integer id;

//	祝日の年
	private String year;

//	祝日の月
	private String month;

//	祝日の日
	private String day;

//	祝日の名称
	private String holidayName;
	
//	選択した日付
	private String selectedDate;

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

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	
}
