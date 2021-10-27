package com.example.linah_alkhurayyif_signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePage : AppCompatActivity() {
    private lateinit var dbHandler: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        dbHandler = DatabaseHandler(this)
        var name = intent.extras?.getString("name")!!
        var email =intent.extras?.getString("email")!!
        var phonenum =intent.extras?.getString("phonenum")!!

        title_tv.text="hi ${name} .."
        email_tv.text= email
        phonenum_tv.text= phonenum
        signOutbtn.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}