
package com.example.fuawan_oblig1.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QuizScreen(uiState: QuizUiState){
    val currentQuestionIndex = remember { mutableStateOf(uiState.currentQuestionIndex)}
    val riktigSvar = remember { mutableStateOf(uiState.riktigSvar)}
    var resultat by remember {mutableStateOf("")}

    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        if (currentQuestionIndex.value < uiState.questions.size) {
            val currentQuestion = uiState.questions[currentQuestionIndex.value]
            Text(text = currentQuestion.text, modifier = Modifier.align(Alignment.CenterHorizontally).padding(32.dp))
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier =
                       Modifier.align(Alignment.CenterHorizontally).padding(32.dp)  ,
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Button(onClick = {


                    if ( currentQuestion.svar == true){
                        riktigSvar.value += 1
                    }
                    currentQuestionIndex.value++
                }, colors = ButtonDefaults.buttonColors(Color.Green),
                modifier =  Modifier.shadow(4.dp),
                shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = "Fakta")
                }
                Button(onClick = {

                    if (currentQuestion.svar == false){
                        riktigSvar.value += 1
                    }
                    currentQuestionIndex.value++
                }, colors = ButtonDefaults.buttonColors(Color.Red),
                modifier =  Modifier.shadow(4.dp),
                shape = RoundedCornerShape(4.dp)) {
                    Text(text = "Fleip")
                }
            }
        } else{

            Text(text = "")
            Spacer(Modifier.height(30.dp))
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(32.dp),
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ){
                //Har den slik for at den faktisk virker usynlig og ikke helt borte
                Button(onClick = {}, modifier = Modifier.alpha(0.0125f).shadow(4.dp),
                shape = RoundedCornerShape(4.dp)){
                    Text(text = "Fakta")
                }
                Button(onClick = {}, modifier = Modifier.alpha(0.0125f).shadow(4.dp),
                shape = RoundedCornerShape(4.dp)){
                    Text(text = "Fleip")
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))



        if(currentQuestionIndex.value >= uiState.questions.size){

            resultat = "Du har svart ${riktigSvar.value} ut av ${uiState.questions.size} spørsmål riktig!"
            Text(
                text = resultat,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(32.dp)
            )

        }else{
            resultat = "Poeng: [${riktigSvar.value}/${uiState.questions.size}]"
            Text(
                text = resultat,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(32.dp)
            )
        }





        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxHeight().fillMaxWidth()
        ) {
            Button(onClick = {
                riktigSvar.value = 0
                currentQuestionIndex.value = 0
            }, modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp)) {
                Text(text = "Reset Quiz")
            }
        }

    }
}


















