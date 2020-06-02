package honey.noteapp.addNote

sealed class AddNoteEvent {
    data class TitleChanged(val title: String) : AddNoteEvent()

    data class DescriptionChanged(val description: String) : AddNoteEvent()

    class SaveClicked() : AddNoteEvent()
}