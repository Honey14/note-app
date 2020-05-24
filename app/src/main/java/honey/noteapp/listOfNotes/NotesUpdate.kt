package honey.noteapp.listOfNotes

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Next.noChange
import com.spotify.mobius.Update

class NotesUpdate : Update<NotesModel, NotesEvent, NotesEffect> {
    override fun update(
        model: NotesModel,
        event: NotesEvent
    ): Next<NotesModel, NotesEffect> {
        return when (event) {
            is HasNotes -> next(model.hasNotesInList(hasNotes = true))
        }
    }
}