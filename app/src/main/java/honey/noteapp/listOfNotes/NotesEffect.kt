package honey.noteapp.listOfNotes

sealed class NotesEffect {

    object GoToAddScreen : NotesEffect()

    object GoToDetailScreen : NotesEffect()
}