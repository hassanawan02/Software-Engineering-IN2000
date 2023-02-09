package com.example.fuawan_oblig1.quiz

import com.example.fuawan_oblig1.quiz.data.Question

data class QuizUiState(val questions : List<Question>){
    val riktigSvar = 0
    var currentQuestionIndex = 0
}
