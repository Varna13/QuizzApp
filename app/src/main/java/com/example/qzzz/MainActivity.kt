package com.example.qzzz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qzzz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf("What is the built-in database in Android studio?",
        "What is the full form of API in Android Development?",
        "In which year, first android was released by Google?")

    private val options = arrayOf(
        arrayOf("MYSQL", "SQLite", "Firebase"),
        arrayOf("Application Program Interface", "Android Program Interface", "Android Package Information"),
        arrayOf("2010", "2006", "2008")
    )

    private val correctAnswer = arrayOf(1, 0, 2)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.btnOption1.setOnClickListener { checkAnswer(0) }
        binding.btnOption2.setOnClickListener { checkAnswer(1) }
        binding.btnOption3.setOnClickListener { checkAnswer(2) }
        binding.tvQuestion.setOnClickListener { restartQuiz() }

    }

    private fun correctButtonColor(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.btnOption1.setBackgroundColor(Color.GREEN)
            1 -> binding.btnOption2.setBackgroundColor(Color.GREEN)
            2 -> binding.btnOption3.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColor(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.btnOption1.setBackgroundColor(Color.RED)
            1 -> binding.btnOption2.setBackgroundColor(Color.RED)
            2 -> binding.btnOption3.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColor(){
        binding.btnOption1.setBackgroundColor(Color.rgb(100, 100, 0))
        binding.btnOption2.setBackgroundColor(Color.rgb(100, 100, 0))
        binding.btnOption3.setBackgroundColor(Color.rgb(100, 100, 0))
    }

    private fun showResult(){
        binding.btnRestart.isEnabled = true
        Toast.makeText(this, "Your score: $score out of ${questions.size}", Toast.LENGTH_SHORT).show()

    }

    private fun displayQuestion(){
        resetButtonColor()
        binding.tvQuestion.text = questions[currentQuestionIndex]
        binding.btnOption1.text = options[currentQuestionIndex][0]
        binding.btnOption2.text = options[currentQuestionIndex][1]
        binding.btnOption3.text = options[currentQuestionIndex][2]

    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswer[currentQuestionIndex]
        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColor(selectedAnswerIndex)
        } else {
            wrongButtonColor(selectedAnswerIndex)
            correctButtonColor(correctAnswerIndex)
        }

        if (currentQuestionIndex < questions.size - 1){
            currentQuestionIndex++
            binding.tvQuestion.postDelayed({displayQuestion()}, 1000)
        } else {
            showResult()
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.btnRestart.isEnabled = false
    }
}