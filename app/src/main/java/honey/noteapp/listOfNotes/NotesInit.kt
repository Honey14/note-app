package honey.noteapp.listOfNotes

import com.spotify.mobius.First
import com.spotify.mobius.Init
import honey.noteapp.listOfNotes.NotesEffect.GetList

class NotesInit : Init<NotesModel, NotesEffect> {
    override fun init(model: NotesModel): First<NotesModel, NotesEffect> {
        return First.first(model, setOf(GetList(model.listOfNotes)))
    }
}
