package com.example.holiday.Controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.holiday.service.ifs.HolidayService;
import com.example.holiday.vo.request.HolidayRequest;
import com.example.holiday.vo.response.HolidayResponse;

//thymeleaf用コントローラー
@CrossOrigin
@Controller
public class HolidayController {

	@Autowired
	private HolidayService holidayService;


	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String load(RedirectAttributes redirectAttributes,Model model) {
		model.addAttribute("holidayRequest", new HolidayRequest());
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		List<String> years = Arrays.asList(String.valueOf(year - 1), String.valueOf(year), String.valueOf(year + 1), "2025");
		model.addAttribute("years", years);
		List<String> holidays = Arrays.asList("元日",
	            "成人の日",
	            "建国記念の日",
	            "天皇誕生日",
	            "春分の日",
	            "昭和の日",
	            "憲法記念日",
	            "みどりの日",
	            "こどもの日",
	            "海の日",
	            "山の日",
	            "敬老の日",
	            "秋分の日",
	            "スポーツの日",
	            "文化の日",
	            "勤労感謝の日",
	            "振替休日");
		model.addAttribute("holidays", holidays);
		
		return "addHoliday";
			
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addHoliday(@ModelAttribute HolidayRequest holidayRequest,RedirectAttributes redirectAttributes,
			Model model) {
		HolidayResponse res = holidayService.addHoliday(holidayRequest);

		redirectAttributes.addFlashAttribute("holidayResponse", res);
		return "redirect:/load";
	}

}
