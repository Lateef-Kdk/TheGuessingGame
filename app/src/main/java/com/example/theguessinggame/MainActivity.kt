package com.example.theguessinggame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log10


class MainActivity : AppCompatActivity() {
    private lateinit var beginningNumber: TextView
    private lateinit var endingNumber: TextView
    private lateinit var howManyGuesses: TextView
    private lateinit var theNumberTheyEntered: TextView
    var begin = 0
    var end = 0
    var numGuesses = 0
    var difference = 0
    var IWontUseTHis = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        beginningNumber = findViewById(R.id.beginTV)
        endingNumber = findViewById(R.id.endTV)
        theNumberTheyEntered = findViewById(R.id.numberTVID)

        //create shared preferences
        val pref = getSharedPreferences("MYPREFS",Context.MODE_PRIVATE)
        val editor = pref.edit()

        findViewById<Button>(R.id.doneBtnID).setOnClickListener {
            if (beginningNumber.text.toString() == "" || endingNumber.text.toString() == "") {
                Toast.makeText(this, "Please pick an Upper AND Lower Limit", Toast.LENGTH_SHORT).show()
            } else if (endingNumber.text.toString().toInt() < beginningNumber.text.toString().toInt()) {
                Toast.makeText(this, "Upper Limit, can not be less than lower limit", Toast.LENGTH_SHORT).show()
            } else if (theNumberTheyEntered.text.toString() == "") {
                Toast.makeText(this, "You have to choose a number!", Toast.LENGTH_SHORT).show()
            } else if ((theNumberTheyEntered.text.toString().toInt() > endingNumber.text.toString().toInt()) ||
                (theNumberTheyEntered.text.toString().toInt() < beginningNumber.text.toString().toInt())) {
                Toast.makeText(this, "That number is out of bounds!", Toast.LENGTH_SHORT).show()
            } else {
                begin = beginningNumber.text.toString().toInt()
                end = endingNumber.text.toString().toInt()
                difference = end - begin

                //calculating how many guesses it would take to get the right answer
                numGuesses = (log10(difference.toDouble()) / log10(2.0)).toInt()+1

                Log.i("Calculations", begin.toString())
                Log.i("Calculations", end.toString())
                Log.i("Calculations", difference.toString())
                Log.i("Calculations", numGuesses.toString())

                //save the calculation for the number of guesses
                editor.putString("NUMGUESSES",numGuesses.toString())
                //save the the value of the lower limit they choose
                editor.putInt("LOWERLIMIT",begin)
                //save the the value of the upper limit they choose
                editor.putInt("UPPERLIMIT",end)
                //commit the changes
                editor.putInt("NUMENTERED",theNumberTheyEntered.text.toString().toInt())
                Log.i("Calculations", "They Entered "+theNumberTheyEntered.text.toString())
                editor.apply()

                startActivity(Intent(this@MainActivity, GuessTheNumber::class.java))
            }
        }
    }
}
