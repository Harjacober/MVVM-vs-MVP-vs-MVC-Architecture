package com.example.mvp_quizapp.takequiz

import com.example.mvvm_quizapp.model.Question

interface TakeQuizView {
    fun showVerdictMessage(isCorrect:Boolean, currScore: Int)
    fun showQuizResult(currScore: Int)
    fun showNewQuestion(question: Question)
}