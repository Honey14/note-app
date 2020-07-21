package honey.noteapp.addNote

sealed class AddNoteEffect {

    data class ValidateInput(val title: String, val desc: String) : AddNoteEffect()

    data class SaveNote(val title: String, val desc: String) : AddNoteEffect()

    object GoToDetailScreen : AddNoteEffect()
}
