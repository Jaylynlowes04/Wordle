package com.example.wordle

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    /**
     * Set each variable from guess1-guess3 to the correct value
     */
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

        bindViews()
        startNewGame()

        gbutton.setOnClickListener { onSubmit() }
        resetButton.setOnClickListener { startNewGame() }
    }

    /**
     * Sets each variable to the proper ID
     */
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
     *   attempts : Int - how many guesses the user has
     *
     *   Initiates the game by setting the variables to default
     *   Enables the guess button and the enter guess field
     */
    fun startNewGame() {
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        attempts = 0

        valGuess1.text = ""
        valcheckGuess1.text = ""
        valGuess2.text = ""
        valcheckGuess2.text = ""
        valGuess3.text = ""
        valcheckGuess3.text = ""
        Answer.text = ""

        enterGuess.text?.clear()
        enterGuess.isEnabled = true
        gbutton.isEnabled = true
        resetButton.visibility = MaterialButton.GONE
        gbutton.visibility = MaterialButton.VISIBLE
    }

    /**
     * Parameters / Fields:
     * Checks if guess is the right length and if the guess is a valid word
     * Lets the user know they cannot guess anymore
     * Gives the user feedback depending on what word they use
     */
    fun onSubmit() {
        val guess = enterGuess.text.toString().uppercase()

        if (guess.length != 4) {
            Toast.makeText(this, "Guess must be a 4-letter word", Toast.LENGTH_SHORT).show()
            return
        }
        if (attempts >= maxAttempts) {
            Toast.makeText(this, "Can't guess anymore!", Toast.LENGTH_SHORT).show()
            return
        }

        val feedback = checkGuess(guess)
        when (attempts) {
            0 -> { valGuess1.text = guess; valcheckGuess1.text = feedback}
            1 -> { valGuess2.text = guess; valcheckGuess2.text = feedback}
            2 -> { valGuess3.text = guess; valcheckGuess3.text = feedback}
        }
        attempts++

        val won = guess == wordToGuess
        if (won || attempts == maxAttempts) {
            endGame(won)
        } else {
            enterGuess.text?.clear()
        }
    }

    /**
     * Ends the game
     * Shows the answer
     * Hides the guess button and shows the reset button
     */
    fun endGame(won: Boolean) {
        enterGuess.isEnabled = false
        gbutton.isEnabled = false

        Answer.text = wordToGuess.lowercase()

        gbutton.visibility = MaterialButton.GONE
        resetButton.visibility = MaterialButton.VISIBLE
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