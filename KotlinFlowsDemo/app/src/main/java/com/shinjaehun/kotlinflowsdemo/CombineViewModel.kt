package com.shinjaehun.kotlinflowsdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

class CombineViewModel: ViewModel() {

    private val user = MutableStateFlow<MistakeUser?>(null)
    private val posts = MutableStateFlow(emptyList<Post>())

    private val isAuthenticated = MutableStateFlow(true)

    private val flow1 = (1..10).asFlow().onEach { delay(1000L) }
    private val flow2 = (10..20).asFlow().onEach { delay(300L) }
    var numberString by mutableStateOf("")
        private set

    private val _profileState = MutableStateFlow<ProfileState?>(null)
    val profileState = _profileState.asStateFlow()

    init {
//        user.combine(posts) { user, posts ->
//            _profileState.value = profileState.value?.copy(
//                profilePicUrl = user?.profilePicUrl,
//                username = user?.username,
//                description = user?.description,
//                posts = posts
//            )
//        }.launchIn(viewModelScope)

        // 이렇게도 가능!
//        viewModelScope.launch {
//            user.combine(posts) { user, posts ->
//                _profileState.value = profileState.value?.copy(
//                    profilePicUrl = user?.profilePicUrl,
//                    username = user?.username,
//                    description = user?.description,
//                    posts = posts
//                )
//            }.collect()
//        }

//        isAuthenticated.combine(user) { isAuthenticated, user ->
//            if(isAuthenticated) user else null
//        }.combine(posts) { user, posts ->
//            user?.let {
//                _profileState.value = profileState.value?.copy(
//                    profilePicUrl = user.profilePicUrl,
//                    username = user.username,
//                    description = user.description,
//                    posts = posts
//                )
//            }
//        }.launchIn(viewModelScope)

        isAuthenticated.combine(user) { isAuthenticated, user ->
            if(isAuthenticated) user else null
        }.combine(posts) { user, posts ->
            user?.let {
                _profileState.value = profileState.value?.copy(
                    profilePicUrl = user.profilePicUrl,
                    username = user.username,
                    description = user.description,
                    posts = posts
                )
            }
        }.launchIn(viewModelScope)

//        flow1.zip(flow2) { number1, number2 ->
//            numberString += "($number1, $number2)\n"
//        }.launchIn(viewModelScope)

        merge(flow1, flow2).onEach {
            numberString += "$it\n"
        }.launchIn(viewModelScope)
    }
}