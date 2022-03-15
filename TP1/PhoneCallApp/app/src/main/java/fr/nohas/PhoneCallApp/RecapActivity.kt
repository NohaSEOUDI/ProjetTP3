package fr.nohas.PhoneCallApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import fr.nohas.PhoneCallApp.R

class RecapActivity : AppCompatActivity(){
    private lateinit var  tvFname : TextView
    private lateinit var  tvLname : TextView
    private lateinit var  tvPhone : TextView
    private lateinit var  tvAge : TextView
    private lateinit var  tvDom : TextView

    private lateinit var  btCancel: Button
    private lateinit var  btSubmit : Button
    private lateinit var  btTheme : Button

    //fichier pour afficher la récaputilatif des données insérée

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recap)

        tvFname = findViewById(R.id.tvPrenom)
        tvLname = findViewById(R.id.tvNom)
        tvPhone = findViewById(R.id.tvTel)
        tvAge = findViewById(R.id.tvAgeP)
        tvDom = findViewById(R.id.tvDomP)

        btCancel = findViewById(R.id.btCancelB)
        btSubmit = findViewById(R.id.btConfirme)
        //on récupére l'intension associé à cette activité


        val fn:String = intent.getStringExtra("First Name").toString()
        val ln:String = intent.getStringExtra("Last Name").toString()
        val age:String = intent.getStringExtra("Age").toString()
        val phone:String = intent.getStringExtra("Num Tel").toString()
        val dom:String = intent.getStringExtra("Domaine").toString()

        //pour setter les variable avec les données récupérer
        tvFname.setText(fn)
        tvLname.setText(ln)
        tvPhone.setText(phone)
        tvAge.setText(age)
        tvDom.setText(dom)

        //button ok pour aller à la dérnière page d'appel
        btSubmit.setOnClickListener{
            val intent2 = Intent(this, DisplayActivity::class.java)
            intent2.putExtra("Num tel",phone)
            startActivity(intent2)
        }



    }
}
