package com.example.holiday.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//祝日テーブルのエンティティ
@Entity
@Table(name = "holidays")
public class Holiday {

//	祝日のID（自動生成）
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

//	祝日の年
	@Column(name = "year")
	private String year;

//	祝日の月
	@Column(name = "month")
	private String month;

//	祝日の日
	@Column(name = "day")
	private String day;

//	祝日の名称
	@Column(name = "holiday_name")
	private String holidayName;

	public Holiday(Integer id, String year, String month, String day, String holidayName) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.day = day;
		this.holidayName = holidayName;
	}

	public Holiday() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public Holiday(String year, String month, String day, String holidayName) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.holidayName = holidayName;
	}

}
