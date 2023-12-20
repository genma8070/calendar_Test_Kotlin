package com.example.holiday.service.impl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.holiday.entity.Event;
import com.example.holiday.entity.Holiday;
import com.example.holiday.repository.HolidayDao;
import com.example.holiday.service.ifs.HolidayService;
import com.example.holiday.vo.request.HolidayRequest;
import com.example.holiday.vo.response.HolidayResponse;

//ニュースのメゾッドを実装するクラス
@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayDao holidayDao;

//	ニュースの削除
	@Override
	public HolidayResponse getHoliday() {
		List<Holiday> holidayList = holidayDao.findAll();
		List<Event> eventList = new ArrayList<>();
		for (Holiday holiday : holidayList) {
			
			String year = holiday.getYear();
            String month = holiday.getMonth();
            String day = holiday.getDay();
            String holidayName = holiday.getHolidayName();
            String content = holidayName.replaceAll("\"", "");
            
            LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);

            Event event = new Event("1", date, content);
            eventList.add(event);

	    }
		return new HolidayResponse(eventList);
		
	}

}
