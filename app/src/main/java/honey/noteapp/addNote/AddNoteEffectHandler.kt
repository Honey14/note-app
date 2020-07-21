package honey.noteapp.addNote

import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer
import honey.noteapp.addNote.AddNoteEffect.SaveNote
import honey.noteapp.addNote.AddNoteEffect.ValidateInput
import honey.noteapp.addNote.AddNoteEvent.*
import honey.noteapp.addNote.ValidationErrors.DescriptionBlank
import honey.noteapp.addNote.ValidationErrors.TitleBlank
import honey.noteapp.database.NoteDao
import honey.noteapp.database.SavingNote

class AddNoteEffectHandler(
    private val noteDao : NoteDao
) : Connectable<AddNoteEffect, AddNoteEvent> {
    override fun connect(output: Consumer<AddNoteEvent>): Connection<AddNoteEffect> {
        return object : Connection<AddNoteEffect> {
            override fun accept(effects: AddNoteEffect) {
                when (effects) {
                    is ValidateInput -> validateInput(effects, output)
                    is SaveNote -> savingNote(effects,output)
                }
            }

            override fun dispose() {
            }

        }
    }

    private fun savingNote(effects: SaveNote, events : Consumer<AddNoteEvent>) {
        noteDao.insertNote(SavingNote(0,title = effects.title, description = effects.desc))

        events.accept(NoteSaved)
    }

    private fun validateInput(effect: ValidateInput, events: Consumer<AddNoteEvent>) {
        val title = effect.title
        val description = effect.desc

        val validationErrors = when {
            title.isEmpty() -> setOf(TitleBlank)
            description.isEmpty() -> setOf(DescriptionBlank)
            title.isEmpty() && description.isEmpty() -> setOf(TitleBlank, DescriptionBlank)
            else -> emptySet()
        }

        val validationEvent = if (validationErrors.isEmpty()) {
            ValidationSucceeded
        } else {
            ValidationFailed(validationErrors)
        }
        events.accept(validationEvent)
    }

}
