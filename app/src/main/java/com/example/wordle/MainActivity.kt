package com.example.wordle

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var enterGuess: EditText
    lateinit var gbutton: Button
    lateinit var resetButton: Button

    lateinit var valGuess1: TextView
    lateinit var valcheckGuess1: TextView
    lateinit var valGuess2: TextView
    lateinit var valcheckGuess2: TextView
    lateinit var valGuess3: TextView
    lateinit var valcheckGuess3: TextView
    lateinit var Answer: TextView

    var wordToGuess: String = ""
    var attempts = 0
    var maxAttempts = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun bindViews(){
        enterGuess = findViewById(R.id.enterGuess)
        gbutton = findViewById(R.id.gButton)
        resetButton = findViewById(R.id.resetButton)

        valGuess1 = findViewById(R.id.Guess1)
        valcheckGuess1 = findViewById(R.id.guessCheck1)
        valGuess2 = findViewById(R.id.Guess2)
        valcheckGuess2 = findViewById(R.id.guessCheck2)
        valGuess3 = findViewById(R.id.Guess3)
        valcheckGuess3 = findViewById(R.id.guessCheck3)
        Answer = findViewById(R.id.Answer)

    }
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}