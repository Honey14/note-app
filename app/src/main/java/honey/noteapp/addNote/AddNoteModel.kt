package honey.noteapp.addNote

data class AddNoteModel(
    val title: String,
    val description: String,
    val error: String
) {
    companion object {
        fun default() = AddNoteModel(title = "", description = "",error = "Something")
    }

    fun titleChanged(title: String): AddNoteModel {
        return copy(title = title)
    }

    fun descriptionChanged(description: String): AddNoteModel {
        return copy(description = description)
    }

    fun invalidFields(error : String) : AddNoteModel{
        return copy(error = error)
    }
}
