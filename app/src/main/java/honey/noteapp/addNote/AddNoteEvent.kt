package honey.noteapp.addNote

sealed class AddNoteEvent {
    data class TitleChanged(val title: String) : AddNoteEvent()

    data class DescriptionChanged(val description: String) : AddNoteEvent()

    class SaveClicked() : AddNoteEvent()

    data class ValidationFailed(val error: Set<ValidationErrors>) : AddNoteEvent()

    object ValidationSucceeded : AddNoteEvent()

    object NoteSaved : AddNoteEvent()
}
