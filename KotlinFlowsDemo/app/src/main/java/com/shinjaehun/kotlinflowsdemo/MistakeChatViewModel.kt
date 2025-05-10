package com.shinjaehun.kotlinflowsdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MistakeChatViewModel: ViewModel() {
    // 로그인했을 때, 프리뷰 상태를 변경하고… 채팅에 사용하는 사용자도 추가해야 하고
    // 이런 걸 동시에 처리하려면??
    private val isLoggedIn = MutableStateFlow(true)
    private val chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    private val users = MutableStateFlow<List<ChatUser>>(emptyList())

    // 이렇게 하세요!
    val chatState = combine(isLoggedIn, chatMessages, users) { isLoggedIn, messages, users ->
        if (isLoggedIn) {
            ChatState(
                userPreviews = users.map { curUser ->
                    ChatUserPreview(
                        user = curUser,
                        lastMessage = messages
                            .filter { it.user == curUser }
                            .maxByOrNull { it.time }
                            ?.message
                    )
                },
                headerTitle = users.firstOrNull()?.name
            )
        } else null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    //users.value로 값에 직접 접근하면 컴파일 에러는 없지만… 다른 값들을 처리할 때 users는 따로 놀기 때문에 race condition에 처하게 된다…
//    val chatState = combine(isLoggedIn, chatMessages) { isLoggedIn, messages ->
//        if (isLoggedIn) {
//            ChatState(
//                userPreviews = users.value.map { curUser ->
//                    UserPreview(
//                        user = curUser,
//                        lastMessage = messages
//                            .filter { it.user == curUser }
//                            .maxByOrNull { it.time }
//                            ?.message
//                    )
//                },
//                headTitle = users.value.firstOrNull()?.name
//            )
//        } else null
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

}

data class ChatState(
    val userPreviews: List<ChatUserPreview>,
    val headerTitle: String?
)

data class ChatUserPreview(
    val user: ChatUser,
    val lastMessage: String?
)

data class ChatMessage(
    val user: ChatUser,
    val message: String,
    val time: Long
)

data class ChatUser (
    val id: String,
    val email: String,
    val name: String,
    val avatarUrl: String
)
