package honey.noteapp.listOfNotes

import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

class NotesEffectHandler : Connectable<NotesEffect, NotesEvent> {
    override fun connect(
        output: Consumer<NotesEvent>
    ): Connection<NotesEffect> {
        return object : Connection<NotesEffect> {
            override fun accept(value: NotesEffect) {

            }

            override fun dispose() {

            }

        }
    }
}