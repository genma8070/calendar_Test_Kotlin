package com.example.holiday.service.ifs;

import com.example.holiday.vo.response.HolidayResponse;

//ニュースのメゾッドを定義するインターフェース
public interface HolidayService {

//	ニュースの追加か更新
	public HolidayResponse getHoliday();
}
