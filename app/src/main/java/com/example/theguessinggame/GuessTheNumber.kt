package com.example.theguessinggame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GuessTheNumber : AppCompatActivity() {

    private lateinit var guessingTextView: TextView
    private lateinit var greaterButton: Button
    private lateinit var lessButton: Button
    private lateinit var count:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_the_number)

        guessingTextView = findViewById(R.id.guessingTVID)
        lessButton = findViewById(R.id.lessButtonID)
        greaterButton = findViewById(R.id.greaterButtonID)
        count = findViewById(R.id.NumCountTVID)

        val pref = getSharedPreferences("MYPREFS",Context.MODE_PRIVATE)
        findViewById<TextView>(R.id.CorrectTVID).text = ""

        var begin = pref.getInt("LOWERLIMIT",0)
        var end = pref.getInt("UPPERLIMIT",0)
        var numGuesses = pref.getString("NUMGUESSES","IDK")
        var i = 0
        var currentNum = (begin + end)/2
        findViewById<TextView>(R.id.nowIGuessTVID).text = getString(R.string.NowIGuess,numGuesses)
        count.text = getString(R.string.countit,i)

        guessingTextView.text = getString(R.string.First_Guess,currentNum)

        lessButton.setOnClickListener {
//            Toast.makeText(this,"Less Pressed",Toast.LENGTH_SHORT).show()
            end = currentNum
            currentNum = (begin + end)/2
            guessingTextView.text = getString(R.string.IsItLessThan,currentNum)
            i++
            count.text = getString(R.string.countit,i)
        }

        findViewById<Button>(R.id.neitherBtnID).setOnClickListener{
            findViewById<TextView>(R.id.CorrectTVID).text = getString(R.string.GotIt,currentNum)
            i++
            count.text = getString(R.string.countit,i)
        }

        greaterButton.setOnClickListener {
        //    Toast.makeText(this,"Less Pressed",Toast.LENGTH_SHORT).show()
            begin = currentNum
            currentNum = (begin + end)/2
            guessingTextView.text = getString(R.string.IsItGreaterThan,currentNum)
            i++
            count.text = getString(R.string.countit,i)
        }

        findViewById<Button>(R.id.restardBtnID).setOnClickListener {
            startActivity(Intent(this@GuessTheNumber, MainActivity::class.java))
        }

    }
}
