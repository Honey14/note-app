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
}