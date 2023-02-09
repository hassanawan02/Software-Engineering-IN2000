package com.example.fuawan_oblig1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PalindromeScreen(
    onNavigateToConverter: () -> (Unit)
){
    var brukerInput by remember { mutableStateOf("") }
    var resultat by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextField(
            value = brukerInput,
            onValueChange = { brukerInput = it },
            label = { Text(text = stringResource(id = R.string.bruker_input), modifier = Modifier.fillMaxWidth()) },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(32.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), shape = RoundedCornerShape(4.dp), onClick = { resultat = if (erPalindrome(brukerInput)) "Dette er en palindrom" else "Dette er ikke en palindrom"
            brukerInput = ""
            focusManager.clearFocus()}) {
            Text(text = stringResource(id = R.string.knapp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = resultat)
        }
        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth()
                .fillMaxHeight()) {
            Button(onClick = onNavigateToConverter, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp)) {
                Text(text = stringResource(id = R.string.neste))
            }
        }
    }
}
private fun erPalindrome(tekst: String): Boolean{
    val reversereString = tekst.reversed().toString()
    return tekst.equals(reversereString, ignoreCase = true)
}
