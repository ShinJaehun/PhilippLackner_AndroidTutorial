package com.shinjaehun.kotlinflowsdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MistakeUserViewModel: ViewModel() {
    // 코드 중복도 문제인데 실수로 localUser 처리를 잘못하면 특정 상황에서 localUser가 달라질 수 있음. 버그 발생 가능성…
//    private val _users = MutableStateFlow<List<MistakeUser>>(emptyList())
//    val users = _users.asStateFlow()
//
//    private val _localUser = MutableStateFlow<MistakeUser?>(null)
//    val localUser = _localUser.asStateFlow()
//
//    fun onUserJoined(user: MistakeUser) {
//        _users.update { it + user }
//        if (user.id == "local") {
//            // _users를 업데이트할 때마다 local user 확인 후 업데이트
//            _localUser.update { user }
//        }
//    }
//
//    fun onUserInfoUpdated(user: MistakeUser) {
//        _users.update {
//            it.map { curUser ->
//                if (curUser.id == user.id) user else curUser
//            }
//        }
//        if (user.id == "local") {
//            // _users를 업데이트할 때마다 local user 확인 후 업데이트
//            _localUser.update { user }
//        }
//    }

    // 이렇게 하세요!
    // flow function을 사용해서 race condition을 없앰…
    private val _users = MutableStateFlow<List<MistakeUser>>(emptyList())
    val users = _users.asStateFlow()

    val localUser = users.map { users ->
        users.find { it.id == "local" }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    // 'stateIn'이 없으면 아무런 일도 일어나지 않음
    // 즉, users가 update 되더라도 localUser를 변경하지 않음.

    fun onUserJoined(user: MistakeUser) {
        _users.update { it + user }
    }

    fun onUserInfoUpdated(user: MistakeUser) {
        _users.update {
            it.map { curUser ->
                if (curUser.id == user.id) user else curUser
            }
        }
    }
}

data class MistakeUser (
    val id: String,
    val email: String,
    val name: String,
    val avatarUrl: String
)
