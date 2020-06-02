package honey.noteapp.addNote

sealed class AddNoteEffect {

    data class ValidateInput(val title: String, val desc: String) : AddNoteEffect()
}