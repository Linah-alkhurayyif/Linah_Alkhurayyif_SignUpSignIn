package com.example.linah_alkhurayyif_signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUp : AppCompatActivity() {
    private lateinit var dbHandler: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        dbHandler = DatabaseHandler(this)
        val pattern: Pattern = Pattern.compile(".+@.+\\.[a-z]+")
        signUpbtn.setOnClickListener {
            if(name_up.text.isEmpty() || email_up.text.isEmpty() || phonenum_up.text.isEmpty() || password_up.text.isEmpty()){
                error_tv.text ="* All fields are required"
            }else{
                val matcher: Matcher = pattern.matcher(email_up.text.toString())
                val matchFound: Boolean = matcher.matches()
                if(!(matchFound)){
                    error_tv.text ="* Email not valid"
                }else if(phonenum_up.text.length != 10){
                    error_tv.text ="* Phone number should be 10 number/character"
                }else if(password_up.text.length <=7){
                    error_tv.text ="* Password should be more then7 number/character"
                }else{
                    error_tv.text = ""
                    dbHandler.addUser(name_up.text.toString(),email_up.text.toString(),phonenum_up.text.toString(),password_up.text.toString())
                    Toast.makeText(this, "Sing up successfully", Toast.LENGTH_LONG).show()
                    intent = Intent(applicationContext, HomePage::class.java)
                    intent.putExtra("name", name_up.text.toString())
                    intent.putExtra("email",email_up.text.toString())
                    intent.putExtra("phonenum",phonenum_up.text.toString())
                    startActivity(intent)
                }
            }
        }
    }
}