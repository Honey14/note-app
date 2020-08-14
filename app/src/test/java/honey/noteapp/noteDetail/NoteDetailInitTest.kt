package honey.noteapp.noteDetail

import com.spotify.mobius.test.FirstMatchers.hasEffects
import com.spotify.mobius.test.FirstMatchers.hasModel
import com.spotify.mobius.test.InitSpec
import com.spotify.mobius.test.InitSpec.assertThatFirst
import honey.noteapp.noteDetail.NoteDetailEffect.LoadNoteDetails
import org.junit.Test

class NoteDetailInitTest {
    @Test
    fun `when clicked on note item in the list, then load note details`() {
        // given
        val initSpec = InitSpec(NoteDetailInit())
        val model = NoteDetailModel("title", "this is a description")
        // then
        initSpec
            .whenInit(model)
            .then {
                assertThatFirst(
                    hasModel(model),
                    hasEffects(LoadNoteDetails(model.title, model.description) as NoteDetailEffect)
                )
            }
    }

}
