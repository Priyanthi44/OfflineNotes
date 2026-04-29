package com.myproject.offlinenotesapp.data.model

data class Note(val id: Int,  val title: String,  val bullets:List<String>) {
}

sealed class UiNotesState(){
    object  Loading: UiNotesState()
    data class Success(val notes:List<Note>): UiNotesState()
    data class Error(val message:String): UiNotesState()

}