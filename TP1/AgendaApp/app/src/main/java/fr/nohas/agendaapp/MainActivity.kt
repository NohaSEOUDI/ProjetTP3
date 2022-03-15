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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView = findViewById(R.id.cv)
        calendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener{_,i,il,i2 ->
            val date = "$i/$il/$i2"
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("date",date)
            startActivity(intent)
        })

    }


}

