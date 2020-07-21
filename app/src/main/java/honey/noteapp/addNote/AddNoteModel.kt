package honey.noteapp.addNote

data class AddNoteModel(
    val title: String,
    val description: String,
    val errors: Set<ValidationErrors>
) {
    companion object {
        fun default() = AddNoteModel(title = "", description = "",errors = emptySet())
    }

    fun titleChanged(title: String): AddNoteModel {
        return copy(title = title)
    }

    fun descriptionChanged(description: String): AddNoteModel {
        return copy(description = description)
    }

    fun invalidFields(errors : Set<ValidationErrors>) : AddNoteModel{
        return copy(errors = errors)
    }
}
