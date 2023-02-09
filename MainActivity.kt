package com.example.fuawan_oblig1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fuawan_oblig1.ui.theme.Fuawan_oblig1Theme
import androidx.compose.foundation.layout.fillMaxSize
import com.example.fuawan_oblig1.quiz.data.Question
import com.example.fuawan_oblig1.quiz.QuizScreen
import com.example.fuawan_oblig1.quiz.QuizUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Fuawan_oblig1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     MyAppNavHost()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "palindrome"
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "palindrome"
    ){
        composable("palindrome"){
            PalindromeScreen(
                onNavigateToConverter = {navController.navigate("converter")},
            )
        }
        composable("converter"){
            UnitConverterScreen(
                onNavigateToQuiz = {navController.navigate("quiz")}
            )
        }
        composable("quiz"){
            val uiState = QuizUiState(questions = listOf(
                Question("Norge er en del av EU", false),
                Question("I in2000 programmerer man i C++", false),
                Question("Det norske flagget er rødt, hvitt og blått", true)
            ))
            QuizScreen(uiState = uiState)
        }



    }
}
