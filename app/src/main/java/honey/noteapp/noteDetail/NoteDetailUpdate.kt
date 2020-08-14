package honey.noteapp.noteDetail

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update
import honey.noteapp.noteDetail.NoteDetailEvent.NoteLoaded

class NoteDetailUpdate : Update<NoteDetailModel, NoteDetailEvent, NoteDetailEffect> {
    override fun update(
        model: NoteDetailModel,
        event: NoteDetailEvent
    ): Next<NoteDetailModel, NoteDetailEffect> {
        return when (event) {
            is NoteLoaded -> next(model.noteDetailsRetrieved(event.title, event.description))
        }
    }
}
