package com.jonaslimah.eletricalcomsumption

import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jonaslimah.eletricalcomsumption.ui.theme.EletricalComsumptionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EletricalComsumptionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .padding(40.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        CalculateApp()
                    }

                }
            }
        }
    }
}

@Composable
fun CalculateApp() {
    InitialLayout(modifier = Modifier)
}

@Composable
fun InitialLayout(modifier: Modifier) {
    Column(
        modifier
            .padding(15.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment . CenterHorizontally
    ) {
        var power by remember {
            mutableStateOf("")
        }
        var hour by remember {
            mutableStateOf("")
        }
        var days by remember {
            mutableStateOf("")
        }
        var kwH by remember {
            mutableStateOf("")
        }

        val powerConvert = power.toIntOrNull() ?: 0
        val hourConvert = hour.toIntOrNull() ?: 0
        val daysConvert = days.toIntOrNull() ?: 0
        val kwHConvert = kwH.toDoubleOrNull() ?: 0.00


        val airConditioner = AirConditioner(
            "ar-condicionado",
            powerConvert
        )
        airConditioner.setTime(hourConvert)
        airConditioner.setDays(daysConvert)
        airConditioner.setKwH(kwHConvert)

        Text(
            text = stringResource(R.string.title),
            modifier,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            softWrap = true

        )
        Spacer(modifier = Modifier.height(20.dp))

        EditField(modifier = Modifier.fillMaxWidth(), KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ), power, onValueChange = {
                if (power.length <= 4) power = it else power = ""
            }, label = stringResource(R.string.power_label), placeholderValue = R.string.power_placeholder)
        Spacer(modifier = Modifier.height(20.dp))
        EditField(
            modifier = Modifier.fillMaxWidth(),
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            hour,
            onValueChange = {
                if (hour.length <= 2) {
                    hour = it
                } else {
                    hour = ""
                }
            },
            label = stringResource(R.string.hour_label),
            placeholderValue = R.string.hours_placeholder
        )
        Spacer(modifier = Modifier.height(20.dp))
        EditField(
            modifier = Modifier.fillMaxWidth(),
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            days,
            onValueChange = {
                if (days.length <= 2) {
                    days = it
                } else {
                    days = ""
                }
            },
            label = stringResource(R.string.days_label),
            placeholderValue = R.string.days_placeholder
        )
        Spacer(modifier = Modifier.height(20.dp))
        EditField(
            modifier = Modifier.fillMaxWidth(),
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            kwH,
            onValueChange = {
                if (kwH.length <= 4) {
                    kwH = it
                } else {
                    kwH = ""
                }
            },
            label = stringResource(R.string.value_days_label),
            placeholderValue = R.string.kwH_placeholder
        )
        Spacer(modifier = Modifier.height(40.dp))


        ResultCalc(airConditioner)


    }
}

@Composable
fun EditField(
    modifier: Modifier,
    keyBoardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    @StringRes
    placeholderValue: Int
) {
    TextField(

        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyBoardOptions,
        placeholder = { Text(text = stringResource(id = placeholderValue)) },
        singleLine = true,
        label = { Text(text = label) }
    )
}

@Composable
fun ResultCalc(
    airConditioner: AirConditioner
) {
    Text(
        text = stringResource(R.string.result_title),
        maxLines = 1,
        textAlign = TextAlign.Center,
        fontSize = 25.sp,
    )

    Text(
        text = "R$ ${airConditioner.consumption()} ",
        maxLines = 1,
        textAlign = TextAlign.Center,
        fontSize = 25.sp,
        overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(20.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    EletricalComsumptionTheme {
        CalculateApp()
    }
}