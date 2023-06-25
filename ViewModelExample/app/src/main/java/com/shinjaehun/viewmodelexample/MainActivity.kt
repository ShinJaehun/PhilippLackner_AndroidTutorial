package com.shinjaehun.viewmodelexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shinjaehun.viewmodelexample.ui.theme.ViewModelExampleTheme

class MainActivity : ComponentActivity() {

//    private val viewModel = ContactsViewModel()
//    private val viewModel by viewModels<ContactsViewModel>() // ViewModel()을 상속 받고 난 이후에는 이렇게 해주는 게 맞나봐...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelExampleTheme {
                // viewModel에 constructional argument가 필요한 경우 ViewModelProvider가 필요!
                val viewModel = viewModel<ContactsViewModel>(
                    factory = object: ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                            return super.create(modelClass)
                            return ContactsViewModel(
                                helloWorld = "Hello World!"
                            ) as T
                        }
                    }
                )

//                val viewModel = viewModel<ContactsViewModel>()
                // 여기서 viewModel()를 사용하려면 gradle에서 lifecycle view model 추가 필요
                // viewModel을 여기서 생성해야 하는 이유가... activity에서 떼어 놓기 위함인거야?
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = viewModel.backgroundColor
                ) {
                    Button(onClick = {
                        viewModel.changeBackgroundColor()
                    }) {
                        Text(text = "Change color")
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewModelExampleTheme {
        Greeting("Android")
    }
}