package com.example.myapplication

import Event
import EventAdapter
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.CalendarDayLayoutBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.CalendarView
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


class BlankFragment : Fragment() {

    private lateinit var years: Spinner
    private lateinit var months: Spinner
    private lateinit var calendarView: com.kizitonwose.calendar.view.CalendarView
    private lateinit var yearItems: Array<String>
    private lateinit var currentMonth: YearMonth
    private lateinit var currentSelectedDate: LocalDate
    private val events = mutableMapOf<LocalDate, List<Event>>()
    private var selectedDate: LocalDate? = null
    private lateinit var eventAdapter: EventAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        calendarView = view.findViewById(R.id.calendarView)

        currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(50)
        val endMonth = currentMonth.plusMonths(50)
        val firstDayOfWeek = firstDayOfWeekFromLocale()



        calendarView.setup(startMonth, endMonth, firstDayOfWeek)
        calendarView.scrollToMonth(currentMonth)

//        年スピンナー
        years = view.findViewById(R.id.spinner1)
        val currentYear = YearMonth.now().year
        val startYear = currentYear - 1
        val endYear = currentYear + 1
        yearItems = (startYear..endYear).map { it.toString() }.toTypedArray()

        val yearAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            yearItems
        )
        years.adapter = yearAdapter

        val currentYearIndex = yearItems.indexOf(currentMonth.year.toString())
        years.setSelection(currentYearIndex)

        years.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateCalendarView()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

//        月スピンナー
        months = view.findViewById(R.id.spinner2)
        val monthItems = resources.getStringArray(R.array.month_items)
        val monthAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            monthItems
        )
        months.adapter = monthAdapter

        val currentMonthIndex = currentMonth.monthValue - 1
        months.setSelection(currentMonthIndex)

        months.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateCalendarView()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

//        カレンダースクロール
        view.findViewById<CalendarView>(R.id.calendarView).monthScrollListener = {
            currentMonth = YearMonth.of(it.yearMonth.year, it.yearMonth.monthValue)
            updateSpinners(currentMonth.year, currentMonth.monthValue)

        }

        currentSelectedDate = LocalDate.now()

//        仮資料
        addFakeEvents()


        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay
            private val binding = CalendarDayLayoutBinding.bind(view)
            val textView: TextView = binding.calendarDayText
            val leave: View = binding.leave
            val workplace: View = binding.workplace
            val headquarters: View = binding.headquarters

            init {
                view.setOnClickListener {
                    onDayClick(day.date)
                }
            }

            //                日付選択
            private fun onDayClick(date: LocalDate) {
                val selectedEvents = events[date] ?: emptyList()

                showEventDialog(view.context, selectedEvents)
            }

            private fun showEventDialog(context: Context, events: List<Event>) {
                val eventDetails =
                    events.joinToString("\n") { "${it.date} - ${it.type} - ${it.content}" }

                if (eventDetails.isNotEmpty()) {
                    AlertDialog.Builder(context)
                        .setTitle("イベント情報")
                        .setMessage(eventDetails)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }

            fun bind(data: CalendarDay) {
                day = data
                textView.text = data.date.dayOfMonth.toString()


                if (data.position == DayPosition.MonthDate) {
                    if (data.date.dayOfWeek == DayOfWeek.SUNDAY || data.date.dayOfWeek == DayOfWeek.SATURDAY) {
                        textView.setTextColor(Color.RED)
                    } else {
                        textView.setTextColor(Color.BLACK)
                    }
                    val dayEvents = events[data.date]
                    val hasEvent = dayEvents?.isNotEmpty() ?: false
                    leave.visibility =
                        if (dayEvents?.any { it.type == "1" } == true) View.VISIBLE else View.INVISIBLE
                    workplace.visibility =
                        if (dayEvents?.any { it.type == "2" } == true) View.VISIBLE else View.INVISIBLE
                    headquarters.visibility =
                        if (dayEvents?.any { it.type == "3" } == true) View.VISIBLE else View.INVISIBLE
                } else {
                    textView.setTextColor(Color.GRAY)

                }
            }
        }

        calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.bind(data)
            }
        }


        calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, data: CalendarMonth) {

                container.titlesContainer.children.mapNotNull { it as? TextView }
                    .forEachIndexed { index, textView ->
                        val dayOfWeek = daysOfWeek()[index]
                        val title = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                        textView.text = title
                    }
            }
        }

        return view
    }

    //    カレンダー更新
    private fun updateCalendarView() {
        val selectedYear = years.selectedItem.toString().toInt()
        val selectedMonth = months.selectedItemPosition + 1
        val selectedYearMonth = YearMonth.of(selectedYear, selectedMonth)

//        selectDate(selectedYearMonth.atDay(1))
        currentSelectedDate = selectedYearMonth.atDay(1)
        calendarView.scrollToMonth(selectedYearMonth)
    }

    //    スピンナー更新
    private fun updateSpinners(selectedYear: Int, selectedMonth: Int) {
        val currentYearIndex = yearItems.indexOf(selectedYear.toString())
        years.setSelection(currentYearIndex)

        months.setSelection(selectedMonth - 1)

        currentMonth = YearMonth.of(selectedYear, selectedMonth)

    }

    //    日付選択※まだ無理
//    private fun selectDate(date: LocalDate) {
//        if (selectedDate != date) {
//            val oldDate = selectedDate
//            selectedDate = date
//            oldDate?.let { calendarView.notifyDateChanged(it) }
//            calendarView.notifyDateChanged(date)
//
//        }
//    }

    //JSON転換
    private fun parseJsonToEventList(jsonString: String): List<Event> {
        val eventList = mutableListOf<Event>()

        try {
            val jsonObject = JSONObject(jsonString)
            val eventArray = jsonObject.getJSONArray("event")

            for (i in 0 until eventArray.length()) {
                val eventObject = eventArray.getJSONObject(i)
                val type = eventObject.getString("type")
                val dateStr = eventObject.getString("date")
                val content = eventObject.getString("content")

                val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val date = LocalDate.parse(dateStr, dateFormatter)

                val event = Event(type, date, content)
                eventList.add(event)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return eventList
    }

    //    仮資料
    private fun addFakeEvents() {
        val client = OkHttpClient()
        val request = Request.Builder().url("http://192.168.0.27:8080/get_holiday").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("aaaaaaaaaaaaaa", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val res = response.body?.string()
                val eventList = res?.let { parseJsonToEventList(it) }
                if (eventList != null) {
                    eventList.forEach { event ->
                        events[event.date] = events.getOrDefault(event.date, emptyList()) + event
                    }
                }

                Log.d("aaaaaaaaaaaaa", "res: $eventList")
            }
        })
    }
}