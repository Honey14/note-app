package honey.noteapp.database

import android.content.Context
import androidx.room.*

@Database(entities = [SavingNote::class], version = 1)
abstract class SaveNoteDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: SaveNoteDb? = null

        fun getDatabase(context: Context): SaveNoteDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SaveNoteDb::class.java,
                    "notes_db"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

@Entity(tableName = "Notes")
class SavingNote(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var title: String,
    var description: String
)

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: SavingNote)
}
