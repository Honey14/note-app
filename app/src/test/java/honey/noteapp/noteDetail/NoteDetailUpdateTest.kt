package honey.noteapp.noteDetail

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import honey.noteapp.noteDetail.NoteDetailEvent.NoteLoaded
import org.junit.Test

class NoteDetailUpdateTest {
    @Test
    fun `when note details are received, then load the note`() {
        // given
        val updateSpec = UpdateSpec(NoteDetailUpdate())
        val model = NoteDetailModel("title", "description")

        // then
        updateSpec
            .given(model)
            .whenEvents(NoteLoaded(model.title, model.description))
            .then(
                assertThatNext(
                    hasModel(model.noteDetailsRetrieved(model.title, model.description)),
                    hasNoEffects()
                )
            )
    }
}
