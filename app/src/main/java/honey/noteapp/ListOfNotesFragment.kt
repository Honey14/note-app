package honey.noteapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.spotify.mobius.Connection
import com.spotify.mobius.Mobius
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.functions.Consumer
import honey.noteapp.database.SaveNoteDb
import honey.noteapp.listOfNotes.*

class ListOfNotesFragment : Fragment(), NotesUi, UiActions {

    private val controller: MobiusLoop.Controller<NotesModel, NotesEvent> by lazy {
        val noteDao = SaveNoteDb.getDatabase(requireActivity().applicationContext).noteDao()

        MobiusAndroid.controller(
            Mobius.loop(
                NotesUpdate(),
                NotesEffectHandler(uiActions = this, noteDb = noteDao)
            ),
            NotesModel.create()
        )
    }

    private val uiRenderer by lazy {
        NotesUiRenderer(this)
    }

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
                uiRenderer.render(value)
            }

            override fun dispose() {

            }

        }
    }

    override fun showNotes(notes: List<Note>?) {
        Log.d("noteshere1234", "hasnotes")
    }

    override fun showNoNotesText() {
        Log.d("noteshere1234", "NOnotes")

    }

    override fun navigateToAddNoteScreen() {

    }

    override fun navigateToDetailScreen() {

    }
}
