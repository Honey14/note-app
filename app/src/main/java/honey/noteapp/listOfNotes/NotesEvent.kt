package honey.noteapp.listOfNotes

sealed class NotesEvent

object FetchingList : NotesEvent()

data class HasNotes(val notes: List<NotesModel>) : NotesEvent()

object NoNotes : NotesEvent()

object AddClicked : NotesEvent()

object SelectNote : NotesEvent()