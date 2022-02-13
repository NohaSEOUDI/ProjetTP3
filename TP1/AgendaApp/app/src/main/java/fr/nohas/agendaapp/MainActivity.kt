package fr.nohas.agendaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var calendarView: CalendarView
    private lateinit var textVselectedDay: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView = findViewById(R.id.cv)
        textVselectedDay = findViewById(R.id.selectedDay)



        calendarView.setOnDateChangeListener{
            calendarView,i,i2,i3 ->  Toast.makeText(this,"Selected Date:$i3/$i2/$i",Toast.LENGTH_LONG).show()
        }
    }


}

