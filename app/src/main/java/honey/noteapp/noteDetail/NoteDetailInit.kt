package honey.noteapp.noteDetail

import com.spotify.mobius.First
import com.spotify.mobius.Init
import honey.noteapp.noteDetail.NoteDetailEffect.LoadNoteDetails

class NoteDetailInit : Init<NoteDetailModel, NoteDetailEffect> {
    override fun init(model: NoteDetailModel): First<NoteDetailModel, NoteDetailEffect> {
        return First.first(model, setOf(LoadNoteDetails(model.title, model.description)))
    }
}
