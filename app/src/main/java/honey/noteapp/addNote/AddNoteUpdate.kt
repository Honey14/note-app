package honey.noteapp.addNote

import com.spotify.mobius.Next
import com.spotify.mobius.Next.*
import com.spotify.mobius.Update
import honey.noteapp.addNote.AddNoteEffect.*
import honey.noteapp.addNote.AddNoteEvent.*

class AddNoteUpdate : Update<AddNoteModel, AddNoteEvent, AddNoteEffect> {
    override fun update(
        model: AddNoteModel,
        event: AddNoteEvent
    ): Next<AddNoteModel, AddNoteEffect> {
        return when (event) {
            is TitleChanged -> next(model.titleChanged(event.title))
            is DescriptionChanged -> next(model.descriptionChanged(event.description))
            is SaveClicked -> dispatch(setOf(ValidateInput(model.title, model.description)))
            is ValidationFailed -> next(model.invalidFields(event.error))
            is ValidationSucceeded -> dispatch(setOf(SaveNote(model.title,model.description)))
            is NoteSaved -> dispatch(setOf(GoToDetailScreen))
        }
    }

}
