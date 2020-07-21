package honey.noteapp.addNote

import com.nhaarman.mockitokotlin2.mock
import com.spotify.mobius.Connection
import com.spotify.mobius.test.RecordingConsumer
import honey.noteapp.addNote.AddNoteEffect.SaveNote
import honey.noteapp.addNote.AddNoteEffect.ValidateInput
import honey.noteapp.addNote.AddNoteEvent.NoteSaved
import honey.noteapp.addNote.AddNoteEvent.ValidationFailed
import honey.noteapp.addNote.ValidationErrors.DescriptionBlank
import honey.noteapp.addNote.ValidationErrors.TitleBlank
import honey.noteapp.database.NoteDao
import honey.noteapp.listOfNotes.UiActions
import org.junit.After
import org.junit.Test

class AddNoteEffectHandlerTest {

    private val receivedEvents = RecordingConsumer<AddNoteEvent>()
    private lateinit var connection: Connection<AddNoteEffect>

    @After
    fun removeDown() {
        connection.dispose()
    }

    @Test
    fun `when validate input effect is received, validate entered title`() {
        //given
        setUpConnection()

        //when
        val effect = ValidateInput(
            title = "",
            desc = "this is a description"
        )
        connection.accept(effect)

        //then
        val expectedErrors = setOf(TitleBlank)
        receivedEvents.assertValues(ValidationFailed(expectedErrors))
    }


    @Test
    fun `when validate input effect is received, validate entered description `() {
        //given
        setUpConnection()

        //when
        val effect = ValidateInput(
            title = "hello there",
            desc = ""
        )
        connection.accept(effect)

        //then
        val expectedErrors = setOf(DescriptionBlank)
        receivedEvents.assertValues(ValidationFailed(expectedErrors))
    }

    @Test
    fun `when save note effect is received, save the note in database`() {
        //given
        val dao = mock<NoteDao>()
        setUpConnection(dao)

        //when
        val title = " hello there"
        val description = "this is a note"
        val effect = SaveNote(title = title, desc = description)

        connection.accept(effect)

        //then
        receivedEvents.assertValues(NoteSaved)

    }


    private fun setUpConnection(
        noteDao: NoteDao = mock()
    ) {
        val fakeUiActions: UiActions = FakeUiActions()
        val addNoteEffectHandler = AddNoteEffectHandler(noteDao, fakeUiActions)
        connection = addNoteEffectHandler.connect(receivedEvents)
    }
}

class FakeUiActions : UiActions {
    override fun navigateToAddNoteScreen() {}

    override fun navigateToDetailScreen() {}

}

