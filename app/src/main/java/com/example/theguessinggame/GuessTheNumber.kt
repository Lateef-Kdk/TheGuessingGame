package com.example.theguessinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GuessTheNumber : AppCompatActivity() {

    private lateinit var guessingTextView: TextView
    private lateinit var greaterButton: Button
    private lateinit var lessButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_the_number)

        guessingTextView = findViewById(R.id.guessingTVID)
        lessButton = findViewById(R.id.lessButtonID)
        greaterButton = findViewById(R.id.greaterButtonID)

        var begin = 0
        var end = 1001
        var i = 0
        var currentNum = (begin + end)/2

        guessingTextView.text = getString(R.string.First_Guess)

        lessButton.setOnClickListener {
            Toast.makeText(this,"Less Pressed",Toast.LENGTH_SHORT).show()
            end = currentNum
            currentNum = (begin + end)/2
            guessingTextView.text = getString(R.string.IsItLessThan,currentNum)
        }

        greaterButton.setOnClickListener {
            Toast.makeText(this,"Less Pressed",Toast.LENGTH_SHORT).show()
            begin = currentNum
            currentNum = (begin + end)/2
            guessingTextView.text = getString(R.string.IsItGreaterThan,currentNum)
        }

        findViewById<Button>(R.id.restardBtnID).setOnClickListener {
            startActivity(Intent(this@GuessTheNumber, MainActivity::class.java))
        }

    }
}
