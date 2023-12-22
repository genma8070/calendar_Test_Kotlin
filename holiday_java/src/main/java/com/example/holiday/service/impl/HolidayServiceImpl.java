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

@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayDao holidayDao;

//	祝日の取得
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

			LocalDate date = LocalDate.parse(year + "-" + String.format("%02d", Integer.parseInt(month)) + "-"
					+ String.format("%02d", Integer.parseInt(day)));

			Event event = new Event("3", date, content);
			eventList.add(event);

		}
		return new HolidayResponse(eventList);
	}

//	祝日の追加
	@Override
	public HolidayResponse addHoliday(HolidayRequest req) {
		String selectedDateStr = req.getSelectedDate();
		LocalDate selectedDate = LocalDate.parse(selectedDateStr);

		String year = String.valueOf(selectedDate.getYear());
		String month = String.valueOf(selectedDate.getMonthValue());
		String day = String.valueOf(selectedDate.getDayOfMonth());
		String holidayName = req.getHolidayName();
		Holiday holiday = new Holiday(year, month, day, holidayName);
		
//		その年に既にその祝日が登録しているかどうかを確認する
		Holiday check = holidayDao.findByYearAndName(year, holidayName);
		if (check == null) {
			holidayDao.save(holiday);
			return new HolidayResponse("祝日追加しました");
		} else {
			return new HolidayResponse(year +"年に既に該当祝日があります");
		}

	}
}
