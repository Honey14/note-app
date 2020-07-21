package honey.noteapp.addNote

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import honey.noteapp.addNote.AddNoteEffect.*
import honey.noteapp.addNote.AddNoteEvent.*
import honey.noteapp.addNote.ValidationErrors.DescriptionBlank
import honey.noteapp.addNote.ValidationErrors.TitleBlank
import org.junit.Test

class AddNoteUpdateTest {
    private val model = AddNoteModel.default()
    private val title = "Title"
    private val description = "Description"

    @Test
    fun `when the user changes the title, the UI should be updated`() {
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

    @Test
    fun `when validate input fails, then show error`() {
        val model = model
            .titleChanged("")
            .descriptionChanged("")
        val validationError = setOf(TitleBlank, DescriptionBlank)
        UpdateSpec(AddNoteUpdate())
            .given(model)
            .whenEvent(ValidationFailed(validationError))
            .then(
                assertThatNext(
                    hasModel(model.invalidFields(validationError)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when the validation succeeded, then save the note`() {
        UpdateSpec(AddNoteUpdate())
            .given(model)
            .whenEvent(ValidationSucceeded)
            .then(
                assertThatNext(
                    hasNoModel(),
                    hasEffects(
                        SaveNote(
                            title = title,
                            desc = description
                        ) as AddNoteEffect
                    )
                )
            )
    }

    @Test
    fun `when note the saved, go to Detail screen`(){
        UpdateSpec(AddNoteUpdate())
            .given(model)
            .whenEvent(NoteSaved)
            .then(
                assertThatNext(
                    hasNoModel(),
                    hasEffects(GoToDetailScreen as AddNoteEffect)
                )
            )
    }
}
