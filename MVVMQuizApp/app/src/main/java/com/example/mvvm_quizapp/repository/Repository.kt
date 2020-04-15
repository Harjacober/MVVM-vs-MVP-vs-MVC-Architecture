package com.example.mvvm_quizapp.repository

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_quizapp.model.Question
import com.example.mvvm_quizapp.model.QuestionStore
import kotlin.properties.Delegates

class Repository {
    private object SingletonHelper{
        val  INSTANCE = Repository()
    }
    companion object {
        fun getInstance():Repository{
            return SingletonHelper.INSTANCE
        }
    }

    lateinit var questionStore: QuestionStore
    var currentQuestion = MutableLiveData<Question>()
    var currScore by Delegates.notNull<Int>()
    var numberOfQuestion = 4
    var answerQuestionLiveData = MutableLiveData<List<Any>>()
    var endGameLiveData = MutableLiveData<Int>()

    fun startGame(){
        questionStore = generateQuestions()
        currScore = 0
        currentQuestion.value = questionStore.getQuestion()
    }

    fun answerQuestion(answerIndex: Int){
        numberOfQuestion--
        if(answerIndex == currentQuestion.value?.answerIndex){
            currScore++
            answerQuestionLiveData.value = listOf(true,currScore)
        }else{
            answerQuestionLiveData.value = listOf(false,currScore)
        }
        if(numberOfQuestion==0){
            endGameLiveData.value = currScore
            resetGame()
        }
        currentQuestion.value = questionStore.getQuestion()
    }
    fun getVerdict(): LiveData<List<Any>>{
        return answerQuestionLiveData
    }
    fun endGame(): LiveData<Int>{
        return endGameLiveData
    }
    fun getNextQuestion(): LiveData<Question>{
        return currentQuestion
    }
    private fun resetGame(){
        questionStore = generateQuestions()
        currentQuestion = MutableLiveData<Question>()
        currScore = 0
        numberOfQuestion = 4
        answerQuestionLiveData = MutableLiveData<List<Any>>()
        endGameLiveData = MutableLiveData<Int>()
    }

    /*fun displayQuestion(){
        Handler().postDelayed(Runnable {
            mEnableTouchEvents = true
            // If this is the last question, ends the game.
            // Else, display the next question.
            if (--numberOfQuestion === 0) { // End the game
                endGame()
            } else {
                currentQuestion = questionStore.getQuestion()
            }
        }, 2000)
    }*/
    private fun generateQuestions(): QuestionStore{
        val question1 = createQuestion(
            "What is the name of the current french president?",
            listOf(
                "François Hollande",
                "Emmanuel Macron",
                "Jacques Chirac",
                "François Mitterand"
            ),
            1
        )
        val question2 = createQuestion(
            "How many countries are there in the European Union?",
            listOf("15", "24", "28", "32"),
            2
        )
        val question3 = createQuestion(
            "Who is the creator of the Android operating system?",
            listOf("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
            0
        )
        val question4 = createQuestion(
            "When did the first man land on the moon?",
            listOf("1958", "1962", "1967", "1969"),
            3
        )
        val question5 = createQuestion(
            "What is the capital of Romania?",
            listOf("Bucarest", "Warsaw", "Budapest", "Berlin"),
            0
        )
        val question6 = createQuestion(
            "Who did the Mona Lisa paint?",
            listOf("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
            1
        )
        val question7 = createQuestion(
            "In which city is the composer Frédéric Chopin buried?",
            listOf("Strasbourg", "Warsaw", "Paris", "Moscow"),
            2
        )
        val question8 = createQuestion(
            "What is the country top-level domain of Belgium?",
            listOf(".bg", ".bm", ".bl", ".be"),
            3
        )
        val question9 = createQuestion(
            "What is the house number of The Simpsons?",
            listOf("42", "101", "666", "742"),
            3
        )
        return QuestionStore(
            listOf(
                question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9
            )
        )
    }
    private fun createQuestion(question: String, options: List<String>, answerIndex:Int): Question {
        return Question(question,options,answerIndex)
    }
}