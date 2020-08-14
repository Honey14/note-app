package honey.noteapp.noteDetail

data class NoteDetailModel(
    val title: String,
    val description: String
) {

    companion object {
        fun create(): NoteDetailModel =
            NoteDetailModel(title = "", description = "")
    }

    fun noteDetailsRetrieved(title: String, description: String): NoteDetailModel {
        return copy(title = title, description = description)
    }
}
