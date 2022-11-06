package com.example.lab05

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.OutputStream
import kotlin.collections.ArrayList
import kotlin.math.log

class TODOApp : AppCompatActivity() {
    var filename = "tasks"
    var items : ArrayList<String?> = ArrayList()
    private var stringBuilder = StringBuilder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todoapp)
        readFromFile()
    }
    fun addItem(view:View){
        writeInFile()
        readFromFile()
    }
    private fun writeInFile(){
        var item : String = findViewById<EditText?>(R.id.tbAddItem).text.toString()
        var outputStream : OutputStream
        try {
            outputStream = openFileOutput(filename,Context.MODE_PRIVATE)
            outputStream.write(item.toByteArray())
            outputStream.close()
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }
    private fun readFromFile(){
        var fileInputStream = openFileInput(filename)
        var inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        stringBuilder = StringBuilder()
        var text : String? = null
        while(run {
                text = bufferedReader.readLine()
                text
            } != null){
            items.add(text)
        }

        var myAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        var listview : ListView = findViewById(R.id.lvItems)
        listview.adapter = myAdapter
    }

}