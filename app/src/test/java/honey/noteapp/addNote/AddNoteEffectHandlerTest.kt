package honey.noteapp.addNote

import com.spotify.mobius.Connection
import com.spotify.mobius.test.RecordingConsumer
import honey.noteapp.addNote.AddNoteEffect.ValidateInput
import honey.noteapp.addNote.AddNoteEvent.ValidationFailed
import honey.noteapp.addNote.ValidationErrors.DescriptionBlank
import honey.noteapp.addNote.ValidationErrors.TitleBlank
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
    fun `when validate input effect is received, validate entered description `(){
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

    private fun setUpConnection() {
        val addNoteEffectHandler = AddNoteEffectHandler()
        connection = addNoteEffectHandler.connect(receivedEvents)
    }
}

