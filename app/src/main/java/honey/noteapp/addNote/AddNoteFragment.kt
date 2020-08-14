package honey.noteapp.addNote

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.spotify.mobius.Connection
import com.spotify.mobius.Mobius
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.functions.Consumer
import honey.noteapp.R
import honey.noteapp.addNote.AddNoteEvent.*
import honey.noteapp.addNote.ValidationErrors.DescriptionBlank
import honey.noteapp.addNote.ValidationErrors.TitleBlank
import honey.noteapp.database.SaveNoteDb
import honey.noteapp.listOfNotes.UiActions
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_add_note.view.*

class AddNoteFragment : Fragment(), UiActions {

    private val controller: MobiusLoop.Controller<AddNoteModel, AddNoteEvent> by lazy {
        val noteDao = SaveNoteDb.getDatabase(requireContext().applicationContext).noteDao()

        MobiusAndroid.controller(
            Mobius.loop(
                AddNoteUpdate(),
                AddNoteEffectHandler(uiActions = this, noteDao = noteDao)
            ),
            AddNoteModel.default()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)
        controller.connect { events ->
            connectEvents(view, events)
        }
        return view
    }

    private fun connectEvents(
        view: View,
        events: Consumer<AddNoteEvent>
    ): Connection<AddNoteModel> {

        view.note_title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                events.accept(TitleChanged(s.toString()))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
        view.note_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                events.accept(DescriptionChanged(s.toString()))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        view.buttonSave.setOnClickListener {
            events.accept(SaveClicked())
        }
        return object : Connection<AddNoteModel> {
            override fun accept(value: AddNoteModel) {
                if (TitleBlank in value.errors) {
                    note_title.error = "Please enter title"
                } else {
                    note_title.error = null
                }

                if (DescriptionBlank in value.errors) {
                    note_text.error = "Please enter description"
                } else {
                    note_text.error = null
                }
            }

            override fun dispose() {
                view.note_text.addTextChangedListener(null)
                view.note_title.addTextChangedListener(null)
                view.buttonSave.setOnClickListener(null)
            }

        }
    }

    override fun navigateToAddNoteScreen() {

    }

    override fun navigateToDetailScreen() {
    }

    override fun onResume() {
        super.onResume()
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
