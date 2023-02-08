package com.example.shootman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shootman.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var hangmanModelView: HangmanModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // New word
        hangmanModelView = HangmanModelView()
        hangmanModelView.getNewWord(binding.hangmanTextOutput)

        // Letter input
        binding.guessButton.setOnClickListener {
            val char = binding.guessLetterInput.text.getOrNull(0)
            if(char != null) {
                hangmanModelView.guessLeter(char, binding.hangmanTextOutput, this)
            }
            else {
                Toast.makeText(this, "You must submit a letter", Toast.LENGTH_SHORT).show()
            }
            binding.guessLetterInput.setText("")
        }
    }
}