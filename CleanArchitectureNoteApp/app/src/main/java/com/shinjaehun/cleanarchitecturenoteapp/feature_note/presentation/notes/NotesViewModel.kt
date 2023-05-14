package com.shinjaehun.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinjaehun.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.shinjaehun.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import com.shinjaehun.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.shinjaehun.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel // viewModel과 HiltViewModel의 차이점은?
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    // 이거 어디서 많이 봤던거지?
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null // Job!!

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class && // 반드시 class를 붙여서 비교해야 함!
                        state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    // function 같지만 사실 class
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel() // Job!!
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value =state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}