package honey.noteapp.listOfNotes

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import honey.noteapp.listOfNotes.NotesEffect.GoToAddScreen
import honey.noteapp.listOfNotes.NotesEffect.GoToDetailScreen
import org.junit.Test

class NotesUpdateTest {

    private val spec = UpdateSpec(NotesUpdate())
    private val defaultModel = NotesModel.create()

    @Test
    fun `when list of notes is fetched, then user has notes`() {
        val notes = listOf(
            Note("title1", "description1"),
            Note("title2", "description2"),
            Note("title3", "description3")
        )
        spec
            .given(defaultModel)
            .whenEvent(ListFetched(notes))
            .then(
                assertThatNext(
                    hasModel(defaultModel.notesRetrieved(notes)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when add clicked, go to AddNote screen`() {
        spec
            .given(defaultModel)
            .whenEvent(AddClicked)
            .then(
                assertThatNext(
                    hasNoModel(),
                    hasEffects(GoToAddScreen as NotesEffect)
                )
            )
    }

    @Test
    fun `when a note is selected, then go to Detail screen`() {
        spec
            .given(defaultModel)
            .whenEvent(SelectNote)
            .then(
                assertThatNext(
                    hasNoModel(),
                    hasEffects(GoToDetailScreen as NotesEffect)
                )
            )
    }
}
