package com.example.timetogo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScheduleAdapter(private var schedules: List<Schedule>) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    inner class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val time: TextView = view.findViewById(R.id.scheduleTime)
        val location: TextView = view.findViewById(R.id.scheduleLocation)
        val transport: TextView = view.findViewById(R.id.scheduleTransport)
        val departureTime: TextView = view.findViewById(R.id.scheduleDepartureTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = schedules[position]
        holder.time.text = schedule.time
        holder.location.text = schedule.location
        holder.transport.text = schedule.transport
        holder.departureTime.text = schedule.departureTime
    }

    override fun getItemCount() = schedules.size

    // 데이터를 갱신하는 메소드 추가
    fun updateSchedules(newSchedules: List<Schedule>) {
        schedules = newSchedules
        notifyDataSetChanged()
    }
}

