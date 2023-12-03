package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade_Tap()
                }
            }
        }
    }
}

@Composable
fun Lemonade_Tap() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Cabecera()
        CambioVista()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        Lemonade_Tap()
    }
}

@Composable
fun Cabecera() {
    Column(
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.Yellow)
                .fillMaxWidth()
                .padding(vertical = 25.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.app_name),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CambioVista() {
    var posicion by remember { mutableStateOf(1) }
    var numClicks by remember { mutableStateOf(0) }

    when (posicion) {
        1 -> {
            Cuerpo(
                foto = R.drawable.lemon_tree,
                texto = R.string.lemon_select,
                descripcion = R.string.lemon_tree,
                nextImage = {
                    numClicks = (2..4).random()
                    posicion++
                }
            )
        }

        2 -> {
            Cuerpo(
                foto = R.drawable.lemon_squeeze,
                texto = R.string.lemon_tap_squeeze,
                descripcion = R.string.lemon,
                nextImage = {
                    numClicks--
                    if (numClicks == 0) {
                        posicion++
                    }
                }
            )
        }

        3 -> {
            Cuerpo(
                foto = R.drawable.lemon_drink,
                texto = R.string.lemon_tap_drink,
                descripcion = R.string.glass_lemonade,
                nextImage = {
                    posicion++
                }
            )
        }

        4 -> {
            Cuerpo(
                foto = R.drawable.lemon_restart,
                texto = R.string.lemon_start,
                descripcion = R.string.empty_glass,
                nextImage = {
                    posicion = 1
                }
            )
        }
    }
}

@Composable
fun Cuerpo(
    foto: Int,
    texto: Int,
    descripcion: Int,
    nextImage: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color(195, 236, 210))
                .padding(horizontal = 30.dp, vertical = 20.dp)
        ) {
            Image(
                painter = painterResource(foto),
                contentDescription = stringResource(descripcion),
                modifier = Modifier
                    .clickable { nextImage() }
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(texto),
            )
        }
    }
}