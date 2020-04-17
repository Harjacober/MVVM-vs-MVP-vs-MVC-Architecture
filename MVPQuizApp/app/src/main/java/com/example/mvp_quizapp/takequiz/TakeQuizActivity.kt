package com.example.mvp_quizapp.takequiz

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mvp_quizapp.R
import com.example.mvvm_quizapp.model.Question

class TakeQuizActivity : AppCompatActivity(), TakeQuizView, View.OnClickListener {
    private val presenter = TakeQuizPresenter(this, TakeQuizInteractor())

    lateinit var questionTextView: TextView
    lateinit var optionA : Button
    lateinit var optionB : Button
    lateinit var optionC : Button
    lateinit var optionD : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_quiz)

        //initialize all views
        questionTextView = findViewById(R.id.tv_question)
        optionA = findViewById(R.id.option_a)
        optionB = findViewById(R.id.option_b)
        optionC = findViewById(R.id.option_c)
        optionD = findViewById(R.id.option_d)

        //set tags to identify buttons
        optionA.tag = 0
        optionB.tag = 1
        optionC.tag = 2
        optionD.tag = 3

        //set all button listeners
        optionA.setOnClickListener(this)
        optionB.setOnClickListener(this)
        optionC.setOnClickListener(this)
        optionD.setOnClickListener(this)

        presenter.startGame()
    }

    override fun showVerdictMessage(isCorrect: Boolean, currScore: Int) {
        if(isCorrect){
            Toast.makeText(this, "Correct! Your Score is $currScore", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this, "Wrong! Your Score is $currScore", Toast.LENGTH_LONG).show()
        }
    }

    override fun showQuizResult(currScore: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Well done!")
            .setMessage("Your score is $currScore")
            .setPositiveButton("RETAKE") { _, _ ->
                // End the activity
                val intent = Intent(this, TakeQuizActivity::class.java)
                startActivity(intent)
            }
            .setCancelable(false)
            .create()
            .show()
    }

    override fun showNewQuestion(question: Question) {
        questionTextView.text = question.question
        optionA.text = question.options[0]
        optionB.text = question.options[1]
        optionC.text = question.options[2]
        optionD.text = question.options[3]
    }


    override fun onClick(v: View?) {
        val responseIndex = v?.tag as Int
         presenter.userAnswersQuestion(responseIndex)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
