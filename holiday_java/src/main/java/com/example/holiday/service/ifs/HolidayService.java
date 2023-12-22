package com.example.holiday.service.ifs;

import com.example.holiday.vo.request.HolidayRequest;
import com.example.holiday.vo.response.HolidayResponse;

//祝日のメゾッドを定義するインターフェース
public interface HolidayService {

//	祝日の取得
	public HolidayResponse getHoliday();
//	祝日の追加
	public HolidayResponse addHoliday(HolidayRequest req);
}
