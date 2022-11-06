package com.example.lab05

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayContacts : AppCompatActivity() {
    private val dbName = "addressBook"
    lateinit var db : SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_contacts)
        db = openOrCreateDatabase(dbName, MODE_PRIVATE,null)
        getAllData("contacts")
    }
    private fun getAllData(table:String){
        val query = "SELECT * FROM $table"
        val cursor: Cursor = db.rawQuery(query,null)
        var data = ""
        if(cursor.count > 0)
        {
            cursor.moveToFirst()
            do {
                data += cursor.getString(1)+"\n"
            }
            while (cursor.moveToNext())
        }
        var label : TextView = findViewById(R.id.txtshowData)
        label.text = data
    }
}