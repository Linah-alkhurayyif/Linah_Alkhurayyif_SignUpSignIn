package com.example.linah_alkhurayyif_signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignIn : AppCompatActivity() {
    private lateinit var dbHandler: DatabaseHandler
    lateinit var userList:ArrayList<Users>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        dbHandler = DatabaseHandler(this)
        userList= arrayListOf()
        val pattern: Pattern = Pattern.compile(".+@.+\\.[a-z]+")
        signInbtn.setOnClickListener {
            if(email_in.text.isEmpty() || password_in.text.isEmpty()){
                error_signin_tv.text="* All fields are required"
            }else{
                val matcher: Matcher = pattern.matcher(email_in.text.toString())
                val matchFound: Boolean = matcher.matches()
                if(!(matchFound)){
                    error_signin_tv.text ="* Email not valid"
                }else{
                    userList = dbHandler.viewUsers(email_in.text.toString())
                    userList.shuffle()
                    if(userList.size ==0){
                        error_signin_tv.text="* Email not found"
                    }else{
                        if(password_in.text.toString()!=userList[0].password){
                            error_signin_tv.text="* Incorrect password!"
                        }else{
                            Toast.makeText(this, "Sing in successfully", Toast.LENGTH_LONG).show()
                            intent = Intent(applicationContext, HomePage::class.java)
                            intent.putExtra("name", userList[0].name)
                            intent.putExtra("email",userList[0].email)
                            intent.putExtra("phonenum",userList[0].phonenum)
                            startActivity(intent)
                        }
                }


                }


            }
        }
    }
}