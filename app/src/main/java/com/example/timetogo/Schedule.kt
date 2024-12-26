package com.example.timetogo

import android.os.Parcel
import android.os.Parcelable

class Schedule(
    val date: String,          // 날짜 (yyyy-MM-dd)
    val time: String,          // 시간 (HH:mm)
    val location: String,      // 장소
    val transport: String,     // 이동수단
    val duration: String,      // 예상 소요시간
    val departureTime: String  // 출발 시간
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeString(location)
        parcel.writeString(transport)
        parcel.writeString(duration)
        parcel.writeString(departureTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Schedule> {
        override fun createFromParcel(parcel: Parcel): Schedule {
            return Schedule(parcel)
        }

        override fun newArray(size: Int): Array<Schedule?> {
            return arrayOfNulls(size)
        }
    }
}
