package honey.noteapp.noteDetail

sealed class NoteDetailEffect {

    data class LoadNoteDetails(val title: String, val description: String) : NoteDetailEffect()
}
