package honey.noteapp.listOfNotes

sealed class NotesEvent

data class ListFetched(val notes: List<Note>?) : NotesEvent()

object AddClicked : NotesEvent()

object SelectNote : NotesEvent()
