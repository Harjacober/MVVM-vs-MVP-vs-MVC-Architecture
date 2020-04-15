package com.example.mvvm_quizapp.model

class Question (question: String, options: List<String>, answerIndex: Int){
    var question = question
    var options = options
    var answerIndex = answerIndex
        set(value){
            field = if(value>=0 && value<=options.size) value else throw IllegalArgumentException("Answer index is out of bound")
        }
}

class QuestionStore(var questionList: List<Question>) {
    private var nextQuestionIndex: Int

    init {
        //Shuffle QuestionList before storing it
        questionList = questionList.shuffled()
        nextQuestionIndex = 0
    }

    fun getQuestion(): Question {
        //loop over the question and return one at each call
        if (nextQuestionIndex == questionList.size) {
            nextQuestionIndex = 0;
        }
        return questionList[nextQuestionIndex++]
    }
}