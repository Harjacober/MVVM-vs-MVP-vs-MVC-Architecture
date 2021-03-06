package com.example.mvvm_quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_quizapp.model.Question
import com.example.mvvm_quizapp.model.User
import com.example.mvvm_quizapp.repository.Repository

class QuizViewModel: ViewModel(){
    fun saveUserInformation(name: String){
        Repository.getInstance().saveUserInformation(name)
    }
    fun startGame(){
        Repository.getInstance().startGame()
    }
    fun answerQuestion(answerIndex: Int){
        Repository.getInstance().answerQuestion(answerIndex)
    }
    fun getVerdict():LiveData<List<Any>>{
        return Repository.getInstance().getVerdict()
    }
    fun endGame(): LiveData<User>{
        return Repository.getInstance().endGame()
    }
    fun getNextQuestion(): LiveData<Question>{
        return Repository.getInstance().getNextQuestion()
    }
    fun enableTouchEvent(): LiveData<Boolean>{
        return Repository.getInstance().mEnableTouchEvents
    }
}