package com.example.mvvm_quizapp

import com.example.mvvm_quizapp.model.Question
import com.example.mvvm_quizapp.model.QuestionStore
import com.example.mvvm_quizapp.repository.Repository
import org.junit.Test

class QuestionStoreTest {
    @Test
    fun getQuestion_Method_Returns_a_Question(){
        val question = Question(
            "What is the house number of The Simpsons?",
            listOf("42", "101", "666", "742"),
            3
        )
        val questionList = listOf(question) //generate all questions
        assert(QuestionStore(questionList).getQuestion() == question) //confirm the questions have been created
        val repository = Repository.getInstance()
        repository.startGame()
        assert(repository.questionStore.questionList.isNotEmpty()) //confirms there's a question when the game has started
        var currQuestion = repository.questionStore.getQuestion()
        assert(repository.currentQuestion.value == currQuestion) //confirm that the getQuestion method returns the current question
        val correctAnsIndex = currQuestion.answerIndex
        repository.answerQuestion(correctAnsIndex)
        assert(repository.answerQuestionLiveData.value?.get(0) as Boolean) //confirm that the answer question endpoint updates the answelivedata correctly
        val wrongAnsIndex = if(currQuestion.answerIndex>0) currQuestion.answerIndex-1 else currQuestion.answerIndex+1
        repository.answerQuestion(wrongAnsIndex)
        assert(!(repository.getVerdict().value?.get(0) as Boolean) && repository.getVerdict().value?.get(0)==1)
        assert(repository.currScore == 1)
        currQuestion = repository.questionStore.getQuestion()
        assert(repository.getNextQuestion().value == currQuestion)

    }
}