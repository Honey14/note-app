package honey.noteapp.listOfNotes

import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer
import honey.noteapp.listOfNotes.NotesEffect.GoToAddScreen
import honey.noteapp.listOfNotes.NotesEffect.GoToDetailScreen

class NotesEffectHandler(
    private val uiActions: UiActions
) : Connectable<NotesEffect, NotesEvent> {
    override fun connect(
        output: Consumer<NotesEvent>
    ): Connection<NotesEffect> {
        return object : Connection<NotesEffect> {
            override fun accept(effect: NotesEffect) {
                when (effect) {
                    is GoToAddScreen -> uiActions.navigateToAddNoteScreen()
                    is GoToDetailScreen -> uiActions.navigateToDetailScreen()
                }
            }

            override fun dispose() {

            }

        }
    }
}