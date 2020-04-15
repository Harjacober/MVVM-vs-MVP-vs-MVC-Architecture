package com.example.mvvm_quizapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm_quizapp.databinding.ActivityMainBinding
import com.example.mvvm_quizapp.viewmodel.QuizViewModel

class MainActivity : AppCompatActivity() {

    lateinit var quizViewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        quizViewModel.startGame()
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.lifecycleOwner = this
        activityMainBinding.quizviewmodel = quizViewModel
        activityMainBinding.currQuestion = quizViewModel.getNextQuestion()

        onClick()
        endGame()
    }

    private fun onClick() {
        quizViewModel.getVerdict().observe(this, Observer { data ->
            val score = data[1]
            if(data[0] as Boolean){
                Toast.makeText(this, "Correct! Your Score is $score", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Wrong! Your Score is $score", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun endGame() {
        quizViewModel.endGame().observe(this, Observer { score ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Well done!")
                .setMessage("Your score is $score")
                .setPositiveButton("RETAKE") { _, _ ->
                    // End the activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .setCancelable(false)
                .create()
                .show()
        })
    }
}

