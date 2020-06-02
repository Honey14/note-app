package honey.noteapp.addNote

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Next.noChange
import com.spotify.mobius.Update
import honey.noteapp.addNote.AddNoteEvent.DescriptionChanged
import honey.noteapp.addNote.AddNoteEvent.TitleChanged

class AddNoteUpdate : Update<AddNoteModel, AddNoteEvent, AddNoteEffect> {
    override fun update(
        model: AddNoteModel,
        event: AddNoteEvent
    ): Next<AddNoteModel, AddNoteEffect> {
        return when (event) {
            is TitleChanged -> next(model.titleChanged(event.title))
            is DescriptionChanged -> next(model.descriptionChanged(event.description))
        }
    }

}