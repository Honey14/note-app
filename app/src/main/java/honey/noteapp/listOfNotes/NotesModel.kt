package honey.noteapp.listOfNotes

data class NotesModel(
    val note: Note?,
    val listOfNotes: List<Note>?
) {

    val hasNotes: Boolean
        get() = listOfNotes != null && listOfNotes.isNotEmpty()

    companion object {
        fun create(): NotesModel =
            NotesModel(note = null, listOfNotes = emptyList())
    }

    fun notesRetrieved(listOfNotes: List<Note>?): NotesModel {
        return copy(listOfNotes = listOfNotes)
    }
}
