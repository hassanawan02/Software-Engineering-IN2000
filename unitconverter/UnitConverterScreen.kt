package com.example.fuawan_oblig1

import android.widget.Button
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.core.text.isDigitsOnly

import kotlinx.coroutines.selects.select
import java.text.NumberFormat




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ExposedDropdownMenuBoxScope.() -> Unit
): Unit{}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverterScreen(
    onNavigateToQuiz: () -> Unit
){
    var brukerInput by remember { mutableStateOf("") }
    var antall by remember { mutableStateOf("")}
    var resultat by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current


    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    var expanded by remember { mutableStateOf(false) }
    //Fikk problemer ved å bruke stringArrayResource, så måtte legge dem inn manuelt
    val liste = listOf("Fluid ounce", "Cup", "Gallon", "Hogshead")
    var selectedOptionText by remember { mutableStateOf(liste[0]) }

    if (selectedOptionText == "Fluid ounce"){
        antall = 0.023957.toString()
    } else if(selectedOptionText == "Cup"){
        antall = 0.23659.toString()
    }else if(selectedOptionText == "Gallon"){
        antall = 3.78541.toString()
    }else if(selectedOptionText == "Hogshead"){
        antall = 238.481.toString()
    }







    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(8.dp)) {

        TextField(
            value = brukerInput,
            onValueChange = { brukerInput = it },
            label = { Text(text = stringResource(id = R.string.liste_valg), modifier = Modifier.fillMaxWidth()) },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(32.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )


        androidx.compose.material3.ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {expanded = !expanded},
        ) {
            TextField(
                modifier = Modifier.menuAnchor().padding(32.dp),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { selectedOptionText = it},
                label = { Text("Enhet") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                colors = ExposedDropdownMenuDefaults.textFieldColors(),

            )


            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                liste.forEach { selectedOption ->
                    DropdownMenuItem(
                        text = { Text(selectedOption) },
                        onClick = {
                            selectedOptionText = selectedOption
                            expanded = false

                        },

                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )

                }

            }

        }


        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), shape = RoundedCornerShape(4.dp),onClick = {
            /*
            brukerInput == "" || brukerInput.toDouble()/1 != brukerInput.toDouble()

             */
            if (brukerInput != "" && brukerInput.isDigitsOnly() ) {

                val summen = antall.toDouble() * brukerInput.toDouble()
                val formattert = "%.2f".format(summen)

                resultat =
                    formattert.toString() + " " + selectedOptionText



                focusManager.clearFocus()

            }else {

                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Ugyldig"
                    )
                }

            }
        }) {
            Text(text = stringResource(id = R.string.knapp))

        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text( text = resultat)
        }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            content = { innerPadding ->
                Text(
                    text = "",
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .wrapContentSize()
                )
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxHeight().fillMaxWidth()
                ) {
                    Button(onClick = onNavigateToQuiz, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp)) {

                        Text(text = stringResource(id = R.string.neste))

                    }
                }
            }

        )




    }
}




