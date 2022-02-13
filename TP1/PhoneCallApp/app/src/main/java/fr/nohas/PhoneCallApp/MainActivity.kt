package fr.nohas.PhoneCallApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import fr.nohas.PhoneCallApp.R


class MainActivity : AppCompatActivity() {

    private lateinit var  etFname : EditText
    private lateinit var  etLname : EditText
    private lateinit var  etPhone : EditText
    private lateinit var  etAge : EditText
    private lateinit var  etDom : EditText
    private lateinit var  btValidate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variable= findViewById(Ressource.id.IdentifiantDéfinitDansLaPageXML)
        etFname = findViewById(R.id.etPrenom)
        etLname = findViewById(R.id.etNom)
        etPhone = findViewById(R.id.etphone)
        etAge = findViewById(R.id.etAge)
        etDom = findViewById(R.id.etDom)
        btValidate = findViewById(R.id.btSubmit)

        //Système de validation
        btValidate.setOnClickListener{
            val firstName= etFname.text.toString().trim()
            val lastName= etLname.text.toString().trim()
            val numTel= etPhone.text.toString().trim() //réduit les chaines en éliminants l'espacement
            val domaine= etDom.text.toString().trim()
            if(firstName.isEmpty()){
                etFname.error ="First name required"
                return@setOnClickListener
            }else if(lastName.isEmpty()){
                etLname.error="Last name required"
                return@setOnClickListener
            }else if(numTel.isEmpty()){
                etPhone.error="Phone number required"
                return@setOnClickListener
            }else if(domaine.isEmpty()){
                etDom.error="Domain required"
                return@setOnClickListener
            }else{
                //Toast.makeText(this,R.string.validationCompleted,Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RecapActivity::class.java)
            //une intension: on lui passe l'objet courant + le second classe (On a l'intension d'ouvrir une seconde activité)
                intent.putExtra("First Name",etFname.getText().toString())
                intent.putExtra("Last Name",etLname.getText().toString())
                intent.putExtra("Age",etAge.getText().toString())
                intent.putExtra("Num Tel",etPhone.getText().toString())
                intent.putExtra("Domaine",etDom.getText().toString())
                startActivity(intent)
            }

        }


    }
}