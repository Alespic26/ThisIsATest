package com.example.helloworld

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.AdapterView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworld.ui.theme.HelloWorldTheme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.IntOffset
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.lang.ref.WeakReference
import kotlin.math.roundToInt
import kotlin.random.Random


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HelloWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    GreetingText()

                }
            }
        }
    }

}


@SuppressLint("RememberReturnType")
@Composable
fun GreetingText(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.holycrackers)
    val image2 = painterResource(R.drawable.targetpng)
    val offsetX = remember { mutableStateOf(0f)}
    val offsetY = remember{ mutableStateOf(0f)}

    val randX = remember{ mutableStateOf(0f)}
    val randY = remember{ mutableStateOf(0f)}
    val Score = remember{ mutableStateOf(0f)}

    if(randX.value in (offsetX.value-30)..offsetX.value+30 && randY.value in (offsetY.value-25)..offsetY.value+25){
        randX.value = (Random.nextInt(0,700)-350).toFloat();
        randY.value = (Random.nextInt(200,1700)).toFloat();
        Score.value++
    }
    Box(
    contentAlignment = Alignment.TopCenter)
    {
        Box(modifier = Modifier
            .size((128.dp))
            .offset {
                IntOffset(
                    x = randX.value.roundToInt(),
                    y = randY.value.roundToInt()
                )
            }
        )
        {
            Image(
                painter = image2,
                contentDescription = null,
                modifier = Modifier.size(128.dp, 128.dp)
            )
        }
        Box(modifier = Modifier
            .size(128.dp)
            .offset {
                IntOffset(
                    x = offsetX.value.roundToInt(),
                    y = offsetY.value.roundToInt()
                )
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }
        )
        {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.size(128.dp, 128.dp)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                onClick = {
                    randX.value = (Random.nextInt(0,700)-350).toFloat();
                    randY.value = (Random.nextInt(200,1700)).toFloat();

                })
            {
                Text(text ="Generate a new Target")
            }
            Text(
                text = "Drag the cat to the target!"
            )
        }

    }
    Box(contentAlignment = Alignment.BottomCenter){
        Text(
            text = Score.value.roundToInt().toString(),
            fontSize = 48.sp
        )
    }
}











