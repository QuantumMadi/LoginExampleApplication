package com.example.loginexampleapplication

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_code_validation.*

class CodeValidation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_validation)

        val bundle: Bundle? = intent.extras
        val message = bundle!!.getString(PHONE_NUMBER)
        text.text = "On your number " + message + " a validation code has been sent"

        validateButton.setOnClickListener {
            val intent = Intent(this, AccountProfileInfo::class.java)
            startActivity(intent)
        }

        validCodeTextEdit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkButton()
            }

            private fun checkButton() {
                if (validCodeTextEdit.length()==4) {
                    validateButton.setBackgroundResource(R.color.buttonColor)
                    validateButton.isEnabled=true
                }
                else{
                    validateButton.setBackgroundColor(Color.GRAY)
                    validateButton.isEnabled = false
                }
            }
        })
        validateButton.setBackgroundColor(Color.GRAY)
        validateButton.isEnabled = false
    }
}
