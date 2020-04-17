package com.example.mvp_quizapp.takequiz

import com.example.mvvm_quizapp.model.Question

class TakeQuizPresenter(var takeQuizView: TakeQuizView?, val takeQuizInteractor: TakeQuizInteractor):
TakeQuizInteractor.OnQuizStartListener{

    fun startGame(){
        takeQuizInteractor.startGame(this)
    }
    fun userAnswersQuestion(answerIndex: Int){
        takeQuizInteractor.answerQuestion(answerIndex,this)
    }
    fun onDestroy(){
        takeQuizView = null
    }
    override fun onQuestionAnswered(isCorrect: Boolean, currScore: Int) {
         takeQuizView?.showVerdictMessage(isCorrect, currScore)
    }

    override fun onGameEnded(currScore: Int) {
        takeQuizView?.showQuizResult(currScore)
    }

    override fun onNextQuestionAvailable(question: Question) {
         takeQuizView?.showNewQuestion(question)
    }
}