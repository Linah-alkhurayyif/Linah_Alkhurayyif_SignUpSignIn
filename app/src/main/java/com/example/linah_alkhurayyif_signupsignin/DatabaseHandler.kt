package com.example.linah_alkhurayyif_signupsignin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,"UsersDataBase",null,1){
    var sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if(db != null){
            db.execSQL("CREATE TABLE Users (id INTEGER PRIMARY KEY autoincrement, name TEXT,email TEXT UNIQUE,phonenum TEXT,password TEXT)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}
    fun addUser( name :String,email :String,phonenum :String,password :String): Long{
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("email", email)
        contentValues.put("phonenum", phonenum)
        contentValues.put("password", password)
        val success = sqLiteDatabase.insert("Users", null, contentValues)
        return success
    }


    @SuppressLint("Range")
    fun viewUsers(useremail: String): ArrayList<Users>{
        val userList: ArrayList<Users> = ArrayList()
        val selectQuery = "SELECT * from Users WHERE email=?"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, arrayOf(useremail))
        } catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var email: String
        var phonenum: String
        var password: String
        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                phonenum = cursor.getString(cursor.getColumnIndex("phonenum"))
                password = cursor.getString(cursor.getColumnIndex("password"))
                val user = Users(id = id, name = name,email=email,phonenum=phonenum,password=password)
                userList.add(user)
            } while (cursor.moveToNext())
        }

        return userList
    }
}