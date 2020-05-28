package honey.noteapp.listOfNotes

sealed class NotesEffect {

    data class GetList(val hasNotes: Boolean) : NotesEffect()

    object GoToAddScreen : NotesEffect()

    object GoToDetailScreen : NotesEffect()
}