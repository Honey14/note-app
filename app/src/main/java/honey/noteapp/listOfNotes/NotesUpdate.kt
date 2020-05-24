package honey.noteapp.listOfNotes

import com.spotify.mobius.Next
import com.spotify.mobius.Next.*
import com.spotify.mobius.Update
import honey.noteapp.listOfNotes.NotesEffect.GoToAddScreen

class NotesUpdate : Update<NotesModel, NotesEvent, NotesEffect> {
    override fun update(
        model: NotesModel,
        event: NotesEvent
    ): Next<NotesModel, NotesEffect> {
        return when (event) {
            is HasNotes -> next(model.hasNotesInList(hasNotes = true))
            is NoNotes -> next(model.hasNoNotes(hasNotes = false))
            is AddClicked -> dispatch(setOf(GoToAddScreen))

        }
    }
}