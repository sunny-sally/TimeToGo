package com.example.timetogo


class Schedule (
    val date: String,          // 날짜 (yyyy-MM-dd)
    val time: String,          // 시간 (HH:mm)
    val location: String,      // 장소
    val transport: String,     // 이동수단
    val duration: String,      // 예상 소요시간
    val departureTime: String  // 출발 시간
)