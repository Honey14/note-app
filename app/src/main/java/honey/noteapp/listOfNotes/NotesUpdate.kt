package honey.noteapp.listOfNotes

import com.spotify.mobius.Next
import com.spotify.mobius.Next.*
import com.spotify.mobius.Update
import honey.noteapp.listOfNotes.NotesEffect.GoToAddScreen
import honey.noteapp.listOfNotes.NotesEffect.GoToDetailScreen

class NotesUpdate : Update<NotesModel, NotesEvent, NotesEffect> {
    override fun update(
        model: NotesModel,
        event: NotesEvent
    ): Next<NotesModel, NotesEffect> {
        return when (event) {
            is HasNotes -> next(model.notesRetrieved(hasNotes = true))
            is NoNotes -> next(model.noNotesAvailable(hasNotes = false))
            is AddClicked -> dispatch(setOf(GoToAddScreen))
            is SelectNote -> dispatch(setOf(GoToDetailScreen))
        }
    }
}