package fr.nohas.PhoneCallApp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {
    private lateinit var numTel : TextView
    private lateinit var appel : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        numTel = findViewById(R.id.tv_phoneDispaly)
        appel= findViewById(R.id.btt_call)

       val nT:String = intent.getStringExtra("Num tel").toString()
        numTel.setText(nT)

        appel.setOnClickListener{
            val intent3= Intent(Intent.ACTION_CALL,Uri.parse("tel:"+nT))
            startActivity(intent3)
        }

    }
}