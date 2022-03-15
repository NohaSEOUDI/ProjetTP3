package fr.nohas.agendaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.CalendarView
import android.widget.EditText

class MainActivity2 : AppCompatActivity() {
    private lateinit var editTextMsg: EditText
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        editTextMsg = findViewById(R.id.etTodo)

        editTextMsg.setImeOptions(EditorInfo.IME_ACTION_DONE)
        editTextMsg.setRawInputType(InputType.TYPE_CLASS_TEXT)
    }
}