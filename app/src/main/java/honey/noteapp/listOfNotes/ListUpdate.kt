package honey.noteapp.listOfNotes

import com.spotify.mobius.Next
import com.spotify.mobius.Next.noChange
import com.spotify.mobius.Update

class ListUpdate : Update<ListModel, ListEvent, ListEffect> {
    override fun update(
        model: ListModel,
        event: ListEvent
    ): Next<ListModel, ListEffect> {
        return noChange()
    }
}