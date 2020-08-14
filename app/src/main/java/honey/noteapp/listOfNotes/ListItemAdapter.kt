package honey.noteapp.listOfNotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import honey.noteapp.R
import kotlinx.android.synthetic.main.row_list_of_notes.view.*

class ListItemAdapter(
    private val notes: List<Note>
) : RecyclerView.Adapter<ListItemAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_list_of_notes, parent, false)
        return ListViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentPosition = notes[position]
        holder.title.text = currentPosition.title
        holder.description.text = currentPosition.description
    }


    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.text_headline
        val description: TextView = view.text_details
    }
}
