package com.example.igrwijaya.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCek = findViewById(R.id.btnCek) as Button
        val txtUser = findViewById(R.id.txtUser) as EditText

        btnCek.setOnClickListener {
            //Toast.makeText(this, txtUser.text, Toast.LENGTH_LONG).show()
            val next = Intent(this, Main2Activity::class.java)
            startActivity(next)
        }
    }
}
