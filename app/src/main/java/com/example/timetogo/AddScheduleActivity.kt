package com.example.timetogo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddScheduleActivity : AppCompatActivity() {

    private lateinit var timeEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var transportSpinner: Spinner  // Spinner 변수 추가
    private lateinit var datePicker: DatePicker  // DatePicker 변수 추가
    private lateinit var timePicker: TimePicker  // TimePicker 변수 추가
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_schedule)

        // UI 초기화
        locationEditText = findViewById(R.id.locationEditText)
        transportSpinner = findViewById(R.id.transportSpinner)  // Spinner 초기화
        datePicker = findViewById(R.id.datePicker)  // DatePicker 초기화
        timePicker = findViewById(R.id.timePicker)  // TimePicker 초기화
        saveButton = findViewById(R.id.saveButton)

        // 이동수단 선택을 위한 Spinner 어댑터 설정
        val transportOptions = arrayOf("버스", "지하철", "도보", "자동차")
        val transportAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, transportOptions)
        transportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        transportSpinner.adapter = transportAdapter  // Spinner에 어댑터 설정


        // 일정 저장 버튼 클릭 이벤트
        saveButton.setOnClickListener {
            val time = timeEditText.text.toString()
            val location = locationEditText.text.toString()
            val transport = transportSpinner.selectedItem.toString()  // 선택한 이동수단 가져오기
            val date = "${datePicker.year}-${datePicker.month + 1}-${datePicker.dayOfMonth}"  // 선택한 날짜 가져오기
            val selectedTime = "${timePicker.hour}:${timePicker.minute}"  // 선택한 시간 가져오기

            // 입력된 정보가 모두 채워졌는지 확인
            if (time.isNotEmpty() && location.isNotEmpty()) {
                // 새로운 일정 객체 생성
                val newSchedule = Schedule(
                    date = date,
                    time = selectedTime,
                    location = location,
                    transport = transport,
                    duration = "30분",  // 기본 예상 소요시간
                    departureTime = selectedTime  // 시간을 출발시간으로 설정
                )

                // 메인 액티비티로 돌아가서 새로운 일정을 추가
                val resultIntent = Intent()
                resultIntent.putExtra("newSchedule", newSchedule)  // Parcelable 객체 전달
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // 입력되지 않은 정보가 있을 경우
                Toast.makeText(this, "모든 정보를 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
