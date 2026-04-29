package com.myproject.offlinenotesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.offlinenotesapp.data.model.UiNotesState
import com.myproject.offlinenotesapp.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(val repository: NotesRepository): ViewModel() {

    private  val _uiState = MutableStateFlow<UiNotesState>(UiNotesState.Loading)
    val uiState: StateFlow<UiNotesState> = _uiState.asStateFlow()

    init{
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _uiState.value = UiNotesState.Loading
            try {
               _uiState.value = UiNotesState.Success( repository.getNotes())
            }catch (e: Exception){
                _uiState.value = UiNotesState.Error(e.message.toString())
            }
        }
    }

}