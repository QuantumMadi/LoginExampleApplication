package com.example.loginexampleapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.phoneTextEdit)

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkPhoneNumber()
            }
        })
        authenticateButton.setOnClickListener {
            val message: String = phoneTextEdit.text.toString()
            val intent = Intent(applicationContext, CodeValidation::class.java)
            intent.putExtra(PHONE_NUMBER,message)
            startActivity(intent)
        }
        authenticateButton.isEnabled = false
        authenticateButton.setBackgroundColor(Color.GRAY)
    }

    fun checkPhoneNumber(){

        if(phoneTextEdit.text.length==11) {
            authenticateButton.isEnabled = true
            authenticateButton.setBackgroundColor(Color.CYAN)
        }
        else {
            authenticateButton.isEnabled = false
            authenticateButton.setBackgroundColor(Color.GRAY)
        }
    }
}
