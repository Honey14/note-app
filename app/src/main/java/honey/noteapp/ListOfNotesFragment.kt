package honey.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spotify.mobius.Connection
import com.spotify.mobius.Mobius
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.functions.Consumer
import honey.noteapp.database.SaveNoteDb
import honey.noteapp.listOfNotes.*
import kotlinx.android.synthetic.main.fragment_list_of_notes.*

class ListOfNotesFragment : Fragment(), NotesUi, UiActions {

    private val controller: MobiusLoop.Controller<NotesModel, NotesEvent> by lazy {
        val noteDao = SaveNoteDb.getDatabase(requireActivity().applicationContext).noteDao()

        MobiusAndroid.controller(
            Mobius.loop(
                NotesUpdate(),
                NotesEffectHandler(uiActions = this, noteDb = noteDao)
            ),
            NotesModel.create(),
            NotesInit()
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

            override fun dispose() {}
        }
    }

    override fun showNotes(notes: List<Note>) {
        empty_notes.visibility = View.GONE
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = ListItemAdapter(notes)

    }

    override fun showNoNotesText() {
        empty_notes.visibility = View.VISIBLE

    }

    override fun navigateToAddNoteScreen() {
        findNavController().navigate(R.id.action_listOfNotesFragment_to_addNoteFragment)
    }

    override fun navigateToDetailScreen() {
        findNavController().navigate(R.id.action_listOfNotesFragment_to_noteDetailFragment)
    }

    override fun onStart() {
        super.onStart()
        controller.start()
    }

    override fun onPause() {
        super.onPause()
        controller.stop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        controller.disconnect()
    }
}
