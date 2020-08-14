package honey.noteapp.listOfNotes

import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer
import honey.noteapp.database.NoteDao
import honey.noteapp.listOfNotes.NotesEffect.*

class NotesEffectHandler(
    private val uiActions: UiActions,
    private val noteDb: NoteDao
) : Connectable<NotesEffect, NotesEvent> {
    override fun connect(
        output: Consumer<NotesEvent>
    ): Connection<NotesEffect> {
        return object : Connection<NotesEffect> {
            override fun accept(effect: NotesEffect) {
                when (effect) {
                    is GetList -> retrieveNoteList(output)
                    is GoToAddScreen -> uiActions.navigateToAddNoteScreen()
                    is GoToDetailScreen -> uiActions.navigateToDetailScreen()
                }
            }

            override fun dispose() {

            }

        }
    }

    private fun retrieveNoteList(event: Consumer<NotesEvent>) {
        val noteListReceived = noteDb.retrieveListOfNotes()
        event.accept(ListFetched(noteListReceived))
    }
}
