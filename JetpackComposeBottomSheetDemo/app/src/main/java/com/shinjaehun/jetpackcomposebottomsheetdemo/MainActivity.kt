package com.shinjaehun.jetpackcomposebottomsheetdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shinjaehun.jetpackcomposebottomsheetdemo.ui.theme.JetpackComposeBottomSheetDemoTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeBottomSheetDemoTheme {
                val sheetState = rememberStandardBottomSheetState(
                    initialValue = SheetValue.Hidden,
                    skipHiddenState = false
                )
                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState
                )
                val scope = rememberCoroutineScope()
                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bottom Sheet",
                                fontSize = 30.sp
                            )
                        }
                    },
                    sheetContainerColor = Color.Green
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                scope.launch {
                                    // 부분적으로만 보일 때 클릭하면 hide()가 작동함...
//                                    if (!sheetState.isVisible) {
//                                        sheetState.expand()
//                                    } else {
//                                        sheetState.hide()
//                                    }
                                    when(sheetState.currentValue) {
                                        SheetValue.Hidden -> sheetState.expand()
                                        SheetValue.Expanded -> sheetState.partialExpand()
                                        SheetValue.PartiallyExpanded -> sheetState.expand()
                                    }


                                }
                            }
                        ) {
                            Text(text = "Toggle sheet")
//                            Text(text = "Bottom sheet fraction: ${sheetState.progress.fraction}") // material 3 버전에서는 이거 없음!
                        }
                    }
                }
            }
        }
    }
}
