package honey.noteapp.noteDetail

sealed class NoteDetailEvent {

    data class NoteLoaded(val title: String, val description: String) : NoteDetailEvent()
}
