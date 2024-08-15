package com.shinjaehun.jetpackcomposebeginnertutorial

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shinjaehun.jetpackcomposebeginnertutorial.ui.theme.JetpackComposeBeginnerTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeBeginnerTutorialTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }

//                Greeting("Android")

//                var count = mutableStateOf(0)
//                var count by remember {
//                    mutableStateOf(0)
//                }
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = count.toString(),
//                        fontSize = 30.sp
//                    )
//                    Button(onClick = {
//                        count++
//                    }) {
////                        Text(text = "Click me!: $count")
//                        Text(text = "Click me!")
//                    }

                var name by remember {
                    mutableStateOf("")
                }
                var names by remember {
                    mutableStateOf(listOf<String>())
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { text ->
                                name = text
                            },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(Modifier.width(16.dp))
                        Button(onClick = {
                            if(name.isNotBlank()) {
                                names = names + name
                                name = ""
                            }
                        }) {
                            Text(text="Add")
                        }
                    }
//                    LazyColumn {
//                        items(names) { currentName ->
//                            Text(
//                                text = currentName,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(16.dp)
//                            )
//                            Divider()
//                        }
//                    }

                    NameList(names = names)

                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
////        modifier = modifier,
//        color = Color.Blue,
//        fontSize = 30.sp,
//            modifier = Modifier
//                .background(Color.Red)
//                .padding(16.dp)
//                .background(Color.Green)

//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
////        modifier = Modifier.background(Color.Red)
//        modifier = Modifier
////            .fillMaxSize()
////            .fillMaxWidth()
//            .size(400.dp)
//    ){
//        Text(
//            text = "Hello $name!",
//            color = Color.Blue,
//            fontSize = 30.sp,
////            modifier = Modifier.fillMaxSize()
//        )
//        Text(
//            text = "Some other text",
//            color = Color.Blue,
//            fontSize = 30.sp
//        )

//    Row(
//        horizontalArrangement = Arrangement.End,
//        modifier = Modifier
//            .size(400.dp)
//    ){
//        Text(
//            text = "Hello $name!",
//            color = Color.Blue,
//            fontSize = 30.sp,
//        )
//        Text(
//            text = "Some other text",
//            color = Color.Blue,
//            fontSize = 30.sp
//        )

//    Box(
//        modifier = Modifier
//            .size(400.dp),
////        contentAlignment = Alignment.Center
//    ){
//        Text(
//            text = "Hello $name!",
//            color = Color.Blue,
//            fontSize = 30.sp,
//            modifier = Modifier.align(Alignment.BottomEnd)
//        )
//        Text(
//            text = "Some other text",
//            color = Color.Blue,
//            fontSize = 30.sp
//        )

//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_foreground),
//                contentDescription = null,
//                modifier = Modifier.background(Color.Black)
//        )

//        if(name.length > 5) {
//            Column {
//                for(i in 1 .. 10) {
//                    Icon(
//                        imageVector = Icons.Default.Add,
//                        contentDescription = null
//                    )
//
//                }
//            }
//        }

//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(10) { i ->
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = null,
//                    modifier = Modifier.size(100.dp)
//                )
//            }
//        }

//        LazyRow(modifier = Modifier.fillMaxSize()) {
//            items(10) { i ->
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = null,
//                    modifier = Modifier.size(100.dp)
//                )
//            }
//        }

//    }

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    JetpackComposeBeginnerTutorialTheme {
////        Greeting("Android")
//    }
//}

@Composable
fun NameList(
    names: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(names) { currentName ->
            Text(
                text = currentName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Divider()
        }
    }
}

