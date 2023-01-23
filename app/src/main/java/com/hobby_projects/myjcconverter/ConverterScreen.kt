package com.hobby_projects.myjcconverter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hobby_projects.myjcconverter.ViewModel.CalculateViewModel
import com.hobby_projects.myjcconverter.ViewModel.viewModelFactory
import com.hobby_projects.myjcconverter.ui.theme.MyJCConverterTheme

class ConverterScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        setContent {
            ConverterApp(intent)
        }
    }
}

@Composable
fun ConverterApp(intent: Intent) {
    val focusManagerForUnit1 = LocalFocusManager.current
    val focusManagerForUnit2 = LocalFocusManager.current

    val focusManagerForUnit1Value = LocalFocusManager.current
    val focusManagerForUnit2Value = LocalFocusManager.current

    var unit1Expanded by remember { mutableStateOf(false) }
    var unit2Expanded by remember { mutableStateOf(false) }

    val unit1Icon =
        if (unit1Expanded)
            Icons.Default.KeyboardArrowUp
        else
            Icons.Default.KeyboardArrowDown
    val unit2Icon =
        if (unit2Expanded)
            Icons.Default.KeyboardArrowUp
        else
            Icons.Default.KeyboardArrowDown

    val typeId = intent.getIntExtra("typeId", 0)

    val vm: CalculateViewModel = viewModel(factory = viewModelFactory {
        CalculateViewModel(LocalContext.current, typeId)
    })
    val pattern = Regex("^\\d+\$")

    MyJCConverterTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(
                    title = {
                        val text = when (typeId) {
                            1 -> "Currency Converter"
                            2 -> "Length Converter"
                            3 -> "Area Converter"
                            4 -> "Volume Converter"
                            5 -> "Weight Converter"
                            6 -> "Temperature Converter"
                            7 -> "Speed Converter"
                            8 -> "Pressure Converter"
                            9 -> "Power Converter"
                            else -> "Area Converter"
                        }
                        Text(text = text)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            vm.finishActivity()
                        }) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    elevation = 10.dp
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Column() {
                                OutlinedTextField(
                                    value = vm.unit1,
                                    readOnly = true,
                                    onValueChange = {},
                                    trailingIcon = {
                                        Icon(unit1Icon, null)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onFocusChanged {
                                            unit1Expanded = it.isFocused
                                        }
                                )

                                DropdownMenu(
                                    expanded = unit1Expanded,
                                    onDismissRequest = {
                                        unit1Expanded = false
                                        focusManagerForUnit1.clearFocus()
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    vm.listOfUnits.forEach { label ->
                                        val index = vm.listOfUnits.indexOf(label)
                                        DropdownMenuItem(
                                            onClick = {
                                                vm.unit1 = label
                                                unit1Expanded = false
                                                vm.unit1Changed()
                                                focusManagerForUnit1.clearFocus()
                                            }) {
                                            Text(
                                                modifier = Modifier.padding(start = 16.dp),
                                                text = label
                                            )
                                        }
                                    }
                                }
                            }

                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = vm.unit1Value,
                                maxLines = 1,
                                trailingIcon = {
                                    Icon(
                                        modifier = Modifier.clickable(
                                            onClick = {
                                                vm.unit1Value = ""
                                                vm.unit1ValueChanged(vm.unit1Value)
                                            }
                                        ),
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null)
                                },
                                keyboardActions = KeyboardActions(onDone = { focusManagerForUnit1Value.clearFocus() }),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Number
                                ),
                                onValueChange = {
                                    if (it.isEmpty() || it.matches(pattern)) {
                                        vm.unit1Value = vm.unit1ValueChanged(it)
                                    }
                                },
                                label = {
                                    CheckTextForStyling(vm.unit1Units)
                                }
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            textAlign = TextAlign.End,
                            fontSize = 40.sp,
                            text = "="
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            androidx.compose.foundation.layout.Column() {
                                OutlinedTextField(
                                    value = vm.unit2,
                                    readOnly = true,
                                    onValueChange = {},
                                    trailingIcon = {
                                        Icon(unit2Icon, null)
                                    },
                                    modifier = androidx.compose.ui.Modifier
                                        .fillMaxWidth()
                                        .onFocusChanged {
                                            unit2Expanded = it.isFocused
                                        }
                                )

                                DropdownMenu(
                                    expanded = unit2Expanded,
                                    onDismissRequest = {
                                        unit2Expanded = false
                                        focusManagerForUnit2.clearFocus()
                                    },
                                    modifier = androidx.compose.ui.Modifier
                                        .fillMaxWidth()
                                ) {
                                    vm.listOfUnits.forEach { label ->
                                        val index = vm.listOfUnits.indexOf(label)
                                        DropdownMenuItem(onClick = {
                                            vm.unit2 = label
                                            vm.unit2Changed()
                                            unit2Expanded = false
                                            focusManagerForUnit2.clearFocus()
                                        }) {
                                            Text(
                                                modifier = androidx.compose.ui.Modifier.padding(
                                                    start = 16.dp
                                                ),
                                                text = label
                                            )
                                        }
                                    }
                                }
                            }

                            OutlinedTextField(
                                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                                value = vm.unit2Value,
                                maxLines = 1,
                                trailingIcon = {
                                    Icon(
                                        modifier = Modifier.clickable(
                                            onClick = {
                                                vm.unit2Value = ""
                                                vm.unit2ValueChanged(vm.unit2Value)
                                            }
                                        ),
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null)
                                },
                                keyboardActions = KeyboardActions(onDone = { focusManagerForUnit2Value.clearFocus() }),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Number
                                ),
                                onValueChange = {
                                    if (it.isEmpty() || it.matches(pattern)) {
                                        vm.unit2Value = vm.unit2ValueChanged(it)
                                    }
                                },
                                label = {
                                    CheckTextForStyling(vm.unit2Units)
                                }
                            )
                        }
                    }


                    LazyColumn(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        items(vm.history.reversed().size) { historyRow ->
                            Column {
                                Text(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    text = vm.history.reversed()[historyRow]
                                )
                                Divider(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.Black
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun CheckTextForStyling(thisText: String) {
    if (thisText.contains("(°)")) {
        StyledText(
            type = "Superscript",
            normalText = thisText.substringAfter("(°)"),
            textToStyle = thisText.substringAfter("(").substringBefore(")"),
            true
        )
    } else if (thisText.contains("(")) {
        StyledText(
            type = "Superscript",
            normalText = thisText.substringBefore("("),
            textToStyle = thisText.substringAfter("(").substringBefore(")"),
            false
        )
    } else {
        Text(
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            text = thisText
        )
    }
}
/*

@Composable
fun StyledText(type: String, normalText: String, textToStyle: String, reversed: Boolean) {
    Log.d("VASANT_TAG", "StyledTextFunc: \nNormalText: $normalText \nTextToStyle: $textToStyle \nisReversed: $reversed")
    var mainText = AnnotatedString("")
    var styledText = AnnotatedString("")

    buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        ) {
            mainText = AnnotatedString(normalText)
        }
        if (type == "Superscript") {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.overline.fontSize,
                    fontWeight = FontWeight.Normal,
                    baselineShift = BaselineShift.Superscript
                )
            ) {
                styledText = AnnotatedString(textToStyle)
            }
        } else {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.overline.fontSize,
                    fontWeight = FontWeight.Normal,
                    baselineShift = BaselineShift.Subscript
                )
            ) {
                styledText = AnnotatedString(textToStyle)
            }
        }
    }

    val updatedString =
        if (!reversed)
            mainText + styledText
        else
            styledText + mainText
    Log.d("VASANT_TAG", "FinalText: $updatedString")
    return Text(text = updatedString)
}
*/

@Composable
fun StyledText(type: String, normalText: String, textToStyle: String, reversed: Boolean) {
    return Text(text =
    buildAnnotatedString {
        if (!reversed) {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.subtitle1.fontSize
                )
            ) {
                append(normalText)
            }
        }
        if (type == "Superscript") {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.overline.fontSize,
                    fontWeight = FontWeight.Normal,
                    baselineShift = BaselineShift.Superscript
                )
            ) {
                append(textToStyle)
            }
        } else {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.overline.fontSize,
                    fontWeight = FontWeight.Normal,
                    baselineShift = BaselineShift.Subscript
                )
            ) {
                append(textToStyle)
            }
        }
        if (reversed) {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.subtitle1.fontSize
                )
            ) {
                append(normalText)
            }
        }
    }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyJCConverterTheme {
        val intent = Intent()
        ConverterApp(intent)
    }
}