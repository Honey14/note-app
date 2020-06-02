package honey.noteapp.addNote

sealed class AddNoteEvent {
    data class TitleChanged(val title: String) : AddNoteEvent()
}