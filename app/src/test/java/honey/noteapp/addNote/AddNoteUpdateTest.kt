package honey.noteapp.addNote

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import honey.noteapp.addNote.AddNoteEffect.ValidateInput
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

    @Test
    fun `when the save button is clicked, then validate the input`() {
        val title = "Title"
        val description = "Description"
        val noteModel = model.titleChanged(title).descriptionChanged(description)

        UpdateSpec(AddNoteUpdate())
            .given(noteModel)
            .whenEvent(SaveClicked())
            .then(
                assertThatNext(
                    hasNoModel(),
                    hasEffects(
                        ValidateInput(
                            title = noteModel.title,
                            desc = noteModel.description
                        ) as AddNoteEffect
                    )
                )
            )
    }
}