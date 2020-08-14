package honey.noteapp.noteDetail

import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer
import honey.noteapp.noteDetail.NoteDetailEffect.LoadNoteDetails
import honey.noteapp.noteDetail.NoteDetailEvent.NoteLoaded

class NoteDetailEffectHandler : Connectable<NoteDetailEffect, NoteDetailEvent> {
    override fun connect(output: Consumer<NoteDetailEvent>): Connection<NoteDetailEffect> {
        return object : Connection<NoteDetailEffect> {
            override fun accept(value: NoteDetailEffect) {
                when (value) {
                    is LoadNoteDetails -> output.accept(NoteLoaded(value.title, value.description))
                }
            }

            override fun dispose() {

            }

        }
    }
}
