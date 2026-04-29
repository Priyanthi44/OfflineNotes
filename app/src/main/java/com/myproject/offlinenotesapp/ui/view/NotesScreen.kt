package com.myproject.offlinenotesapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.myproject.offlinenotesapp.data.model.Note
import com.myproject.offlinenotesapp.data.model.UiNotesState
import com.myproject.offlinenotesapp.ui.viewmodel.NotesViewModel

@Composable
fun NotesScreen(viewModel: NotesViewModel = hiltViewModel()){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    when (val state = uiState) {
        is UiNotesState.Loading -> {
            CircularProgressIndicator()
        }
        is UiNotesState.Success -> {
            NoteList(state.notes)
        }
        is UiNotesState.Error -> {
            Text(text = state.message, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun NoteList(notes: List<Note>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(notes, key={it.id } ) { note ->
            NoteCard(note)
        }
    }
}

@Composable
fun NoteCard(note: Note) {
    Card(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge
            )
//            note.bullets.forEach { bullet ->
//                Text(
//                    text = "• $bullet",
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(top = 4.dp)
//                )
//            }
        }
    }
}
