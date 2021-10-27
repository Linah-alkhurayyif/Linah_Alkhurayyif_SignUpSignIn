package com.example.linah_alkhurayyif_signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.linah_alkhurayyif_signupsignin.SignIn
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signIn.setOnClickListener {
            intent = Intent(applicationContext, SignIn::class.java)
            startActivity(intent)
        }
        signUp.setOnClickListener {
            intent = Intent(applicationContext, SignUp::class.java)
            startActivity(intent)
        }
    }
}