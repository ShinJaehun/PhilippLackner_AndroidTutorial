package com.shinjaehun.jetpackcomposecourse

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shinjaehun.jetpackcomposecourse.ui.theme.ComposeCourseTheme
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

//    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
//            ComposeCourseTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }

//            Greeting(name = "ShinJaehun")

// rows, columns and basic sizing

//            Column {
//                Text("Hello")
//                Text("World")
//            }
//
//            Row {
//                Text("Hello")
//                Text("World")
//            }
//
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text("Hello")
//                Text("World")
//            }
//            Greeting("ShinJaehun")

// creating an image card composable

//            val painter = painterResource(R.drawable.bbb)
//            val description = "Cute Rabbit Image"
//            val title = "Cute Rabbit Image"
//
//            Box(
//                modifier = Modifier
//                .fillMaxSize(0.5f)
//                .padding(16.dp)
//            ) {
//                ImageCard(
//                    painter = painter,
//                    contentDescription = description,
//                    title = title,
//                )
//            }

//            ILoveShinJaehun()

// state

//            ColorBox(Modifier.fillMaxSize())

//            Column(Modifier.fillMaxSize()) {
//                val downColor = remember {
//                    mutableStateOf(Color.Yellow)
//                }
//                val upperColor = remember {
//                    mutableStateOf(Color.Black)
//                }
//
//                ColorBox(
//                    Modifier
//                        .weight(1f)
//                        .fillMaxSize()
//                        .background(upperColor.value)
//                ) { down, up ->
//                    downColor.value = down // hosting
//                    upperColor.value = up //
//                }
//                Box(
//                    modifier = Modifier
//                        .background(downColor.value)
//                        .weight(1f)
//                        .fillMaxSize()
//                )
//            }

// textfields, buttons and showing snackbars

//            Snackbar {
//                Text("Hello ShinJaehun")
//            }

//            val snackbarHostState = remember { SnackbarHostState() }
//            val scope = rememberCoroutineScope()
//            var textFieldState by remember {
//                mutableStateOf("")
//            }
//
//            Scaffold(
//                modifier = Modifier.fillMaxSize(),
//                snackbarHost = { SnackbarHost(snackbarHostState) }, // Material 2의 scaffoldState가 deprecated
//            ) { innerPadding ->
//                Column(
//                    modifier = Modifier
////                        .padding(innerPadding)
//                        .padding(16.dp)
//                        .fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    TextField(
//                        value = textFieldState,
//                        label = {
//                            Text("Enter your name")
//                        },
//                        onValueChange = {
//                            textFieldState = it
//                        },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(onClick = {
//                        scope.launch {
//                            snackbarHostState.showSnackbar("Hello $textFieldState")
//                        }
//                    }) {
//                        Text("Please greet me")
//                    }
//                }
//            }

// lists

//            val scrollState = rememberScrollState()
//            Column(
//                modifier = Modifier.verticalScroll(scrollState)
//            ) {
//                for(i in 1..50){
//                    Text(
//                        text = "Item $i",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 24.dp)
//
//                    )
//                }
//            }

//            LazyColumn {
//                items(5000) {
//                    Text(
//                        text = "Item $it",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 24.dp)
//
//                    )
//                }
//            }

//            LazyColumn {
//                itemsIndexed(
//                        listOf("This", "is", "Jetpack", "Compose")
//                ) { index, string ->
//                    Text(
//                        text = "#$index Item $string",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 24.dp)
//
//                    )
//                }
//            }

// constraint layout for jetpack compose

//            val constraints = ConstraintSet {
//                val greenBox = createRefFor("greenbox")
//                val redBox = createRefFor("redbox")
//                val guideline = createGuidelineFromTop(0.5f)
//
//                constrain(greenBox) {
////                    top.linkTo(parent.top)
//                    top.linkTo(guideline)
//                    start.linkTo(parent.start)
//                    width = Dimension.value(100.dp)
//                    height = Dimension.value(100.dp)
//                }
//
//                constrain(redBox) {
//                    top.linkTo(parent.top)
//                    start.linkTo(greenBox.end)
//                    end.linkTo(parent.end)
//                    width = Dimension.value(100.dp)
////                    width = Dimension.fillToConstraints
//                    height = Dimension.value(100.dp)
//                }
//                createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
//            }
//
//            ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
//                Box(
//                    modifier = Modifier
//                        .background(Color.Green)
//                        .layoutId("greenbox")
//                )
//                Box(
//                    modifier = Modifier
//                        .background(Color.Red)
//                        .layoutId("redbox")
//                )
//            }

//            var text by remember {
//                mutableStateOf("")
//            }

//            ComposeCourseTheme {
//                Button(onClick = { text += "#" }) {
//                    i++
//                    Text(text = text)
//                }
//                LaunchedEffect(key1 = text) {
//                    delay(1000L)
//                    println("The text is $text")
//                }
//
//                Button(onClick = { text += "#"}) {
//                    Text(text = text)
//                }

// effect handler : SideEffect

//            Counter()

// effect handler : Recomposition 연구

//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                var count by remember { mutableStateOf(0) }
//                RecompositionCounter(count)
//                Button(onClick = {count++}) {
//                    Text(text = "Increment")
//                }
//            }

//                MyComposable()
//                RunTimerScreen()

// simple animations

//                var sizeState by remember { mutableStateOf(200.dp) }
//                Box(modifier = Modifier
//                    .size(sizeState)
//                    .background(Color.Red),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Button(onClick = {
//                        sizeState += 50.dp
//                    }) {
//                        Text("Increase Size")
//                    }
//                }

//                var sizeState by remember { mutableStateOf(200.dp) }

//                val size by animateDpAsState(targetValue = sizeState)
//                val size by animateDpAsState(
//                    targetValue = sizeState,
//                    tween(
//                        durationMillis = 3000,
//                        delayMillis = 300,
//                        easing = LinearOutSlowInEasing
//                    )
//                )
//                val size by animateDpAsState(
//                    targetValue = sizeState,
//                    spring(
//                        Spring.DampingRatioMediumBouncy
//                    )
//                )
//                val size by animateDpAsState(
//                    targetValue = sizeState,
//                    keyframes {
//                        durationMillis = 5000
//                        sizeState at 0 with LinearEasing // with is deprecated
//                        sizeState * 1.5f at 1000 with FastOutLinearInEasing
//                        sizeState * 2f at 5000
//                    }
//                )
//                val size by animateDpAsState(
//                    targetValue = sizeState,
//                    tween(
//                        durationMillis = 1000,
//                    )
//                )
//                val infiniteTransition = rememberInfiniteTransition()
//                val color by infiniteTransition.animateColor(
//                    initialValue = Color.Red,
//                    targetValue = Color.Green,
//                    animationSpec = infiniteRepeatable(
//                        tween(durationMillis = 2000),
//                        repeatMode = RepeatMode.Reverse
//                    ),
//                )
//                Box(modifier = Modifier
//                    .size(size)
//                    .background(color),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Button(onClick = {
//                        sizeState += 50.dp
//                    }) {
//                        Text("Increase Size")
//                    }
//                }

//            }

// circular progressbar

//            ComposeCourseTheme {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    CircularProgressBar(
//                        percentage = 0.8f,
//                        number = 100
//                    )
//                }
//            }

// Draggable Music Knob

//            ComposeCourseTheme {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color(0xFF101010))
//                ) {
//                    Row(
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
//                            .padding(30.dp)
//                    ) {
//                        var volume by remember {
//                            mutableStateOf(0f)
//                        }
//                        val barCount = 20
//                        MusicKnob(
//                            modifier = Modifier.size(100.dp)
//                        ) {
//                            volume = it
//                        }
//                        Spacer(modifier = Modifier.width(20.dp))
//                        VolumeBar(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(30.dp),
//                            activeBars = (barCount * volume).roundToInt(),
//                            barCount = barCount
//                        )
//                    }
//                }
//            }

        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Green),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceAround
//    ) {
//        Text("Hello")
//        Text(name)
//        Text("World")
//    }

//    Row(
//        modifier = Modifier
////            .fillMaxSize()
////            .fillMaxSize(0.5f)
//            .width(300.dp)
////            .height(300.dp)
//            .fillMaxHeight(0.7f)
//            .background(Color.Green),
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text("Hello")
//        Text(name)
//        Text("World")
//    }

//    Column(
//        modifier = Modifier
//            .background(Color.Green)
//            .fillMaxHeight(0.5f)
////            .width(600.dp)
////            .requiredWidth(600.dp),
//            .fillMaxWidth()
////            .padding(16.dp)
////            .padding(top = 50.dp)
//            .border(5.dp, Color.Magenta)
//            .padding(5.dp)
//            .border(5.dp, Color.Blue)
//            .padding(5.dp)
//            .border(10.dp, Color.Red)
//            .padding(10.dp)
//
////        horizontalAlignment = Alignment.CenterHorizontally,
////        verticalArrangement = Arrangement.SpaceAround
//    ) {
////        Text("Hello")
////        Text("Hello", modifier = Modifier.offset(0.dp, 20.dp))
////        Text("Hello",
////            modifier = Modifier
////                .border(5.dp, Color.Yellow)
////                .padding(5.dp)
////                .offset(20.dp, 20.dp)
////                .border(10.dp, Color.Black)
////                .padding(10.dp)
////        )
//        Text("Hello",
////            modifier = Modifier.clickable {
////
////            }
//        )
//        Spacer(modifier = Modifier.height(50.dp))
//        Text(name)
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeCourseTheme {
//        Greeting("Android")
//    }
//    Greeting("Android")
//}

// creating an image card composable

//@Composable
//fun ImageCard(
//    painter: Painter,
//    contentDescription: String,
//    title: String,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(15.dp),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 5.dp
//        )
//    ) {
//        Box(modifier = Modifier.height(200.dp)) {
//            Image(
//                painter = painter,
//                contentDescription = contentDescription,
//                contentScale = ContentScale.Crop
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        brush = Brush.verticalGradient(
//                            colors = listOf(
//                                Color.Transparent,
//                                Color.Black
//                            ),
//                            startY = 300f
//                        )
//                    )
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(12.dp),
//                contentAlignment = Alignment.BottomStart
//            ) {
//                Text(
//                    title,
//                    style = TextStyle(color = Color.White, fontSize = 16.sp)
//                )
//            }
//        }
//    }
//}

//@Preview
//@Composable
//private fun ImageCardPreview() {
//    val painter = painterResource(R.drawable.bbb)
//    val description = "Cute Rabbit Image"
//    val title = "Cute Rabbit Image"
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize(0.5f)
//            .padding(16.dp)
//    ) {
//        ImageCard(
//            painter = painter,
//            contentDescription = description,
//            title = title,
//        )
//    }
//}

// styling text

//@Composable
//fun ILoveShinJaehun(
//    modifier: Modifier = Modifier
//) {
//    val fontFamily = FontFamily(
//        Font(R.font.lexend_thin, FontWeight.Thin),
//        Font(R.font.lexend_light, FontWeight.Light),
//    )
//    Box(
//        modifier = modifier
//            .fillMaxSize()
//            .background(Color(0xFF101010))
//    ) {
//        Text(
////            text = "I love ShinJaehun",
//            text = buildAnnotatedString {
//                withStyle(
//                    style = SpanStyle(
//                        color = Color.Green,
//                        fontSize = 50.sp
//                    )
//                ) {
//                    append("I")
//                }
//                append(" love")
//                withStyle(
//                    style = SpanStyle(
//                        color = Color.Green,
//                        fontSize = 50.sp
//                    )
//                ) {
//                    append(" ShinJaehun")
//                }
//            },
//            color = Color.White,
//            fontSize = 30.sp,
//            fontFamily = fontFamily,
//            fontWeight = FontWeight.Thin,
//            fontStyle = FontStyle.Italic,
//            textAlign = TextAlign.Center,
//            textDecoration = TextDecoration.Underline
//        )
//    }
//}

//@Preview
//@Composable
//private fun ILoveShinJaehunPreview() {
//    ILoveShinJaehun()
//}

// state

//@Composable
//fun ColorBox(
//    modifier: Modifier = Modifier,
//    updateColor: (Color, Color) -> Unit
//) {
////    val color = mutableStateOf(Color.Yellow) // 이렇게 하면 recomposition 하면서 계속 Yellow가 되버립니다.
////    val color = remember { mutableStateOf(Color.Yellow) }
//
//    Box(
//        modifier = modifier
////            .background(color.value)
////            .clickable {
////                color.value = Color(
////                    Random.nextFloat(),
////                    Random.nextFloat(),
////                    Random.nextFloat(),
////                    1f
////                )
////            }
////            .background(Color.Red)
//            .clickable {
//                updateColor(
//                    Color(
//                        Random.nextFloat(),
//                        Random.nextFloat(),
//                        Random.nextFloat(),
//                        1f,
//                    ),
//                    Color(
//                        Random.nextFloat(),
//                        Random.nextFloat(),
//                        Random.nextFloat(),
//                        1f,
//                    )
//                )
//            }
//    )
//}

// effect handler : Recomposition 연구

//@Composable
//fun RecompositionCounter(newCount: Int) {
//    println("newCount is $newCount")
//    var oldCount by remember { mutableStateOf(newCount) }
//
//    SideEffect {
//        oldCount = newCount
//        Log.i(TAG, "oldCount is ${oldCount}")
//    }
//
//    Column {
//
//        Text("NewCounter ${newCount}")
//        println("new count has been changed from $oldCount to $newCount") // 얘가 있으면 두번 실행된다????
//    }
//}

// effect handler : SideEffect

//@Composable
//fun Counter() {
//    // 카운터에서 사용하기 위해 정의한 state
//    val count = remember { mutableStateOf(0) }
//
//    // count state의 현재 값을 기록하기 위한 SideEffect
//    SideEffect {
//        // Recomposition마다 호출
//        Log.i(TAG, "Count is ${count.value}")
//    }
//
//    Column {
////        Button(onClick = { count.value++ }) {
////            Text("Increase Count")
////        }
////
////        // state가 업데이트 될 때마다, recomposition 실행되면서 텍스트가 변함
////        Text("Counter ${count.value}")
//
////        Button(onClick = { count.value++ }) {
////            Text("Increase Counter ${count.value}?") // 이게 button만 recomposition되니까 그런거지?
////        }
//
//        Button(onClick = { count.value++ }) {
//            SideEffect {
//                Log.i(TAG, "Count in Button is ${count.value}")
//            }
//            Text("Increase Counter ${count.value}?")
//        }
//    }
//}

// effect handler : LaunchedEffect

//@Composable
//fun MyComposable() {
//    val isLoading = remember { mutableStateOf(false) }
//    val data = remember { mutableStateOf(listOf<String>()) }
//
//    // LaunchedEffect는  오랜 시간이 걸리는 작업을 비동기적으로 실행하기 위해 사용한다
//    // LaunchedEffect내의 로직은 isLoading.value 값이 변경될때마다 취소하고 재실행될 것이다.
//    LaunchedEffect(isLoading.value) {
//        if (isLoading.value) {
//            // 장기간의 작업
//            val newData = fetchData()
//            // 기존 데이터를 새로운 데이터로 교체한다.
//            data.value = newData
//            isLoading.value = false
//        }
//    }
//
//    Column {
//        Button(onClick = { isLoading.value = true }) {
//            Text("Fetch Data")
//        }
//        if (isLoading.value) {
//            // 로딩 시, 로딩 인디케이터를 보여준다.
//            CircularProgressIndicator()
//        } else {
//            // 데이터를 보여준다.
//            LazyColumn {
//                items(data.value.size) { index ->
//                    Text(text = data.value[index])
//                }
//            }
//        }
//    }
//}
//
//private suspend fun fetchData(): List<String> {
//    // 네트워크 작업을 본뜨기 위해 일부러 2초 delay를 걺
//    delay(2000)
//    return listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5",)
//}

// effect handler : DisposableEffect

//@Composable
//fun TimerScreen() {
//    val elapsedTime = remember { mutableStateOf(0) }
//
//    DisposableEffect(Unit) {
//        val scope = CoroutineScope(Dispatchers.Default)
//        val job = scope.launch {
//            while (true) {
//                delay(1000)
//                elapsedTime.value += 1
//                Log.i(TAG,"Timer is still working ${elapsedTime.value}")
//            }
//        }
//
//        onDispose {
//            job.cancel()
//        }
//    }
//
//    Text(
//        text = "Elapsed Time: ${elapsedTime.value}",
//        modifier = Modifier.padding(16.dp),
//        fontSize = 24.sp
//    )
//}
//
//@Composable
//fun RunTimerScreen() {
//    val isVisible = remember { mutableStateOf(true) }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Bottom
//    ) {
//        Spacer(modifier = Modifier.height(10.dp))
//
//        if (isVisible.value)
//            TimerScreen()
//
//        Button(onClick = { isVisible.value = false }) {
//            Text("Hide the timer")
//        }
//    }
//}

// circular progressbar

//@Composable
//fun CircularProgressBar(
//    percentage: Float,
//    number: Int,
//    fontSize: TextUnit = 28.sp,
//    radius: Dp = 50.dp,
//    color: Color = Color.Green,
//    strokeWidth: Dp = 8.dp,
//    animDuration: Int = 1000,
//    animDelay: Int = 0,
//) {
//    var animationPlayed by remember { mutableStateOf(false) }
//    val curPercentage = animateFloatAsState(
//        targetValue = if (animationPlayed) percentage else 0f,
//        animationSpec = tween(
//            durationMillis = animDuration,
//            delayMillis = animDelay
//        )
//    )
//    LaunchedEffect(true) {
//        animationPlayed = true
//    }
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.size(radius * 2f)
//    ) {
//        Canvas(
//            modifier = Modifier.size(radius * 2f)
//        ) {
//            drawArc(
//                color = color,
//                startAngle = -90f,
//                sweepAngle = 360 * curPercentage.value,
//                useCenter = false,
//                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
//            )
//        }
//        Text(
//            text = (curPercentage.value * number).toInt().toString(),
//            color = Color.Black,
//            fontSize = fontSize,
//            fontWeight = FontWeight.Bold
//        )
//    }
//}

// Draggable Music Knob

//@Composable
//fun VolumeBar(
//    modifier: Modifier = Modifier,
//    activeBars: Int = 0,
//    barCount: Int = 10
//) {
//    BoxWithConstraints(
//        contentAlignment = Alignment.Center,
//        modifier = modifier
//    ) {
//        val barWidth = remember {
//            constraints.maxWidth / (2f * barCount)
//        }
//        Canvas(modifier = modifier) {
//            for(i in 0 until barCount) {
//                drawRoundRect(
//                    color = if(i in 0..activeBars) Color.Green else Color.DarkGray,
//                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
//                    size = Size(barWidth, constraints.maxHeight.toFloat()),
//                    cornerRadius = CornerRadius(0f)
//                )
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun MusicKnob(
//    modifier: Modifier = Modifier,
//    limitingAngle: Float = 25f,
//    onValueChange: (Float) -> Unit
//) {
//    var rotation by remember {
//        mutableStateOf(limitingAngle)
//    }
//    var touchX by remember {
//        mutableStateOf(0f)
//    }
//    var touchY by remember {
//        mutableStateOf(0f)
//    }
//    var centerX by remember {
//        mutableStateOf(0f)
//    }
//    var centerY by remember {
//        mutableStateOf(0f)
//    }
//
//    Image(
//        painter = painterResource(id = R.drawable.music_knob),
//        contentDescription = "Music knob",
//        modifier = modifier
//            .fillMaxSize()
//            .onGloballyPositioned {
//                val windowBounds = it.boundsInWindow()
//                centerX = windowBounds.size.width / 2f
//                centerY = windowBounds.size.height / 2f
//            }
//            .pointerInteropFilter { event ->
//                touchX = event.x
//                touchY = event.y
//                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()
//
//                when (event.action) {
//                    MotionEvent.ACTION_DOWN,
//                    MotionEvent.ACTION_MOVE -> {
//                        if (angle !in -limitingAngle..limitingAngle) {
//                            val fixedAngle = if (angle in -180f..-limitingAngle) {
//                                360f + angle
//                            } else {
//                                angle
//                            }
//                            rotation = fixedAngle
//
//                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
//                            onValueChange(percent)
//                            true
//                        } else false
//                    }
//                    else -> false
//                }
//            }
//            .rotate(rotation)
//    )
//}