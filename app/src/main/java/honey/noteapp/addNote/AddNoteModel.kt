package honey.noteapp.addNote

data class AddNoteModel(
    val title: String,
    val description: String
) {
    companion object {
        fun default() = AddNoteModel(title = "", description = "")
    }

    fun titleChanged(title: String): AddNoteModel {
        return copy(title = title)
    }

    fun descriptionChanged(description: String): AddNoteModel {
        return copy(description = description)
    }
}