package honey.noteapp.noteDetail

import com.spotify.mobius.Next
import com.spotify.mobius.Next.noChange
import com.spotify.mobius.Update
import honey.noteapp.listOfNotes.Note

class NoteDetailUpdate : Update<Note, NoteDetailEvent, NoteDetailEffect> {
    override fun update(model: Note, event: NoteDetailEvent): Next<Note, NoteDetailEffect> {
        return noChange()
    }
}
