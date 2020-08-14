package honey.noteapp.listOfNotes

interface NotesUi {
    fun showNotes(notes: List<Note>)
    fun showNoNotesText()
}
