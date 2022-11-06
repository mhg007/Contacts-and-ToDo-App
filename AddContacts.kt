package com.example.lab05

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AddContacts : AppCompatActivity() {
    private val dbName = "addressBook"
    lateinit var db : SQLiteDatabase
    var id = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contacts)
    }

    fun addContacts(view:View){
        var name : String = findViewById<EditText?>(R.id.tbName).text.toString()
        var phone : String = findViewById<EditText?>(R.id.tbPhone).text.toString()
        var street : String = findViewById<EditText?>(R.id.tbStreet).text.toString()
        var email : String = findViewById<EditText?>(R.id.tbEmail).text.toString()
        var city : String = findViewById<EditText?>(R.id.tbCity).text.toString()
        createDatabase()
        insertData(id,name,phone,street,email,city)
        id++
        clearTextBoxes()
    }
    private fun createDatabase(){
        db = openOrCreateDatabase(dbName, MODE_PRIVATE,null)
        var tableQuery = "CREATE TABLE IF NOT EXISTS contacts(id INTEGER PRIMARY KEY,name TEXT,phone TEXT,street TEXT,email TEXT,city TEXT)"
        db.execSQL(tableQuery)
    }
    private fun insertData(id:Int,name:String,phone:String,street:String,email:String,city:String){
        val cv = ContentValues()
        cv.put("id",id)
        cv.put("name",name)
        cv.put("phone",phone)
        cv.put("street",street)
        cv.put("email",email)
        cv.put("city",city)
        db.insert("contacts",null,cv)
        Toast.makeText(this, "Contact has been added", Toast.LENGTH_SHORT).show()
    }
    private fun clearTextBoxes(){
        findViewById<EditText>(R.id.tbName).setText("")
        findViewById<EditText>(R.id.tbPhone).setText("")
        findViewById<EditText>(R.id.tbStreet).setText("")
        findViewById<EditText>(R.id.tbEmail).setText("")
        findViewById<EditText>(R.id.tbCity).setText("")

    }
}