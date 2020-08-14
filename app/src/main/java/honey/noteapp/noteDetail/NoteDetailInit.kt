package honey.noteapp.noteDetail

import com.spotify.mobius.First
import com.spotify.mobius.First.first
import com.spotify.mobius.Init
import honey.noteapp.listOfNotes.Note

class NoteDetailInit : Init<Note, NoteDetailEffect> {
    override fun init(model: Note): First<Note, NoteDetailEffect> {
        return first(model)
    }
}
