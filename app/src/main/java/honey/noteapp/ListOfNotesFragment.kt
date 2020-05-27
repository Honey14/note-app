package honey.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.spotify.mobius.Connection
import com.spotify.mobius.Mobius
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.functions.Consumer
import honey.noteapp.listOfNotes.*

class ListOfNotesFragment : Fragment(), UiActions {
    private val effectHandler = NotesEffectHandler(uiActions = this)

    private val mobiusLoop: MobiusLoop.Builder<NotesModel, NotesEvent, NotesEffect> =
        Mobius.loop(NotesUpdate(), effectHandler)

    private val controller: MobiusLoop.Controller<NotesModel, NotesEvent> =
        MobiusAndroid.controller(mobiusLoop, NotesModel.create())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_list_of_notes, container, false)
        controller.connect { events ->
            connectEvents(view, events)
        }
        return view
    }

    private fun connectEvents(view: View, events: Consumer<NotesEvent>): Connection<NotesModel> {
        return object : Connection<NotesModel> {
            override fun accept(value: NotesModel) {

            }

            override fun dispose() {

            }

        }
    }

    override fun navigateToAddNoteScreen() {

    }

    override fun navigateToDetailScreen() {

    }
}
