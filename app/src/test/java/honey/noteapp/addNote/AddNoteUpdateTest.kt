package honey.noteapp.addNote

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import honey.noteapp.addNote.AddNoteEvent.*
import org.junit.Test

class AddNoteUpdateTest {
    private val model = AddNoteModel.default()

    @Test
    fun `when the user changes the title, the UI should be updated`() {
        val title = "happy Day"
        UpdateSpec(AddNoteUpdate())
            .given(model)
            .whenEvent(TitleChanged(title))
            .then(
                assertThatNext(
                    hasModel(model.titleChanged(title)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when the user changes the description, then UI should be updated`() {
        val description = "Today was a happy day"
        UpdateSpec(AddNoteUpdate())
            .given(model)
            .whenEvent(DescriptionChanged(description))
            .then(
                assertThatNext(
                    hasModel(model.descriptionChanged(description)),
                    hasNoEffects()
                )
            )
    }
}