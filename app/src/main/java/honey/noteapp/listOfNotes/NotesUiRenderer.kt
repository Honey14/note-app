package honey.noteapp.listOfNotes

class NotesUiRenderer(private val ui: NotesUi) {

    fun render(model: NotesModel) {
        if (model.hasNotes) {
            ui.showNotes(model.listOfNotes!!)
        } else {
            ui.showNoNotesText()
        }
    }
}
