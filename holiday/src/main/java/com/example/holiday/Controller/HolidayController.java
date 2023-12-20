package com.example.holiday.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.holiday.service.ifs.HolidayService;
import com.example.holiday.vo.response.HolidayResponse;

//ニュースのメゾッドを制御するクラス
@CrossOrigin
@RestController
public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;
	
//	ニュースの追加か更新
	@GetMapping("/get_holiday")
	public HolidayResponse getHoliday() {
		return holidayService.getHoliday();
	};

}
