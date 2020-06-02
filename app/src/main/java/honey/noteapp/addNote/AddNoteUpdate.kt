package honey.noteapp.addNote

import com.spotify.mobius.Next
import com.spotify.mobius.Next.noChange
import com.spotify.mobius.Update

class AddNoteUpdate : Update<AddNoteModel, AddNoteEvent, AddNoteEffect> {
    override fun update(
        model: AddNoteModel,
        event: AddNoteEvent
    ): Next<AddNoteModel, AddNoteEffect> {
        return noChange()
    }

}