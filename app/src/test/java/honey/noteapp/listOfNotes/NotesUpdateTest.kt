package honey.noteapp.listOfNotes

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class NotesUpdateTest {

    private val spec = UpdateSpec(NotesUpdate())
    private val defaultModel = NotesModel.create()

    @Test
    fun `when list of notes is fetched, then user has notes`() {
        val notes = listOf(
            NotesModel("title1", "description1", true),
            NotesModel("title2", "description2", true),
            NotesModel("title3", "description3", true)
        )
        spec
            .given(defaultModel)
            .whenEvent(HasNotes(notes))
            .then(
                assertThatNext(
                    hasModel(defaultModel.hasNotesInList(hasNotes = true)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when list of notes is fetched, then user gets no notes`() {
        spec
            .given(defaultModel)
            .whenEvent(NoNotes)
            .then(
                assertThatNext(
                    hasModel(defaultModel.hasNoNotes(hasNotes = false))
                )
            )
    }
}