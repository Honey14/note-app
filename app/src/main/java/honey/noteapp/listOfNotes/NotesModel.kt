package honey.noteapp.listOfNotes

data class NotesModel(
    val title: String,
    val description: String,
    val hasNotes: Boolean
) {

    companion object {
        fun create(): NotesModel = NotesModel(title = "", description = "", hasNotes = false)
    }

    fun notesRetrieved(hasNotes: Boolean): NotesModel {
        return copy(hasNotes = hasNotes)
    }

    fun noNotesAvailable(hasNotes: Boolean): NotesModel {
        return copy(hasNotes = hasNotes)
    }
}