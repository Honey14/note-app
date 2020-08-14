package honey.noteapp.listOfNotes

sealed class NotesEffect {

    data class GetList(val notes: List<Note>?) : NotesEffect()

    object GoToAddScreen : NotesEffect()

    object GoToDetailScreen : NotesEffect()
}
