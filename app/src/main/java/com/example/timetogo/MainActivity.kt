package com.example.timetogo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var scheduleRecyclerView: RecyclerView
    private val scheduleList = mutableListOf<Schedule>() // 일정 리스트
    private lateinit var scheduleAdapter: ScheduleAdapter

    private lateinit var addScheduleButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI 초기화
        calendarView = findViewById(R.id.calendarView)
        scheduleRecyclerView = findViewById(R.id.scheduleRecyclerView)

        addScheduleButton = findViewById(R.id.addScheduleButton)

        // RecyclerView 설정
        scheduleAdapter = ScheduleAdapter(scheduleList)
        scheduleRecyclerView.layoutManager = LinearLayoutManager(this)
        scheduleRecyclerView.adapter = scheduleAdapter

        // 임시 일정 데이터 추가 (예시)
        scheduleList.add(Schedule("2024-12-26", "10:00", "카페", "버스", "30분", "09:30"))
        scheduleList.add(Schedule("2024-12-26", "14:00", "도서관", "도보", "15분", "13:45"))
        scheduleList.add(Schedule("2024-12-27", "16:00", "영화관", "지하철", "40분", "15:20"))
        scheduleList.add(Schedule("2024-12-28", "18:00", "음악회", "자동차", "1시간", "17:00"))

        // 캘린더 날짜 선택 이벤트
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // 선택한 날짜를 문자열로 변환
            val selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)

            // 선택한 날짜의 일정만 필터링하여 업데이트
            filterSchedulesByDate(selectedDate)

            // 선택한 날짜를 Toast로 출력
            Toast.makeText(this, "선택한 날짜: $selectedDate", Toast.LENGTH_SHORT).show()
        }

        // 일정 추가 버튼 클릭 시 AddScheduleActivity로 이동
        addScheduleButton.setOnClickListener {
            val intent = Intent(this, AddScheduleActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    // 선택한 날짜의 일정 필터링
    private fun filterSchedulesByDate(selectedDate: String) {
        // 일정 리스트에서 선택된 날짜와 일치하는 일정만 필터링
        val filteredSchedules = scheduleList.filter { it.date == selectedDate }

        // RecyclerView의 어댑터에 필터된 일정 리스트를 갱신
        updateScheduleList(filteredSchedules)
    }

    // RecyclerView에 필터된 일정 리스트 업데이트
    private fun updateScheduleList(filteredSchedules: List<Schedule>) {
        // 기존 어댑터의 데이터만 갱신
        scheduleAdapter.updateSchedules(filteredSchedules)
    }

    // AddScheduleActivity에서 데이터를 받아오는 메소드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            val newSchedule = data?.getSerializableExtra("newSchedule") as? Schedule
            newSchedule?.let {
                // 새 일정을 RecyclerView에 추가
                // scheduleAdapter.addSchedule(it) 또는 scheduleList에 추가 후 업데이트
                scheduleList.add(it)
                scheduleAdapter.notifyDataSetChanged()
            }
        }
    }
}







