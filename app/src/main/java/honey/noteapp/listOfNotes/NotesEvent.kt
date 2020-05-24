package honey.noteapp.listOfNotes

sealed class NotesEvent

data class HasNotes(val notes: List<NotesModel>) : NotesEvent()

object NoNotes : NotesEvent()
