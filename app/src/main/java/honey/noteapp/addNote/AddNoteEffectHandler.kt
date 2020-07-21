package honey.noteapp.addNote

import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer
import honey.noteapp.listOfNotes.UiActions

class AddNoteEffectHandler(
    private val uiActions: UiActions
) : Connectable<AddNoteEffect, AddNoteEvent> {
    override fun connect(output: Consumer<AddNoteEvent>): Connection<AddNoteEffect> {
        return object : Connection<AddNoteEffect> {
            override fun accept(effects: AddNoteEffect) {
            }

            override fun dispose() {
            }

        }
    }

}
