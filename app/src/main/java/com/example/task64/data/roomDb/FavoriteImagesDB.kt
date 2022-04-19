package example.task64.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.task64.data.model.ImageModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(ImageModel::class) , version = 1 , exportSchema = false)
abstract class FavoriteImagesDB : RoomDatabase() {
    abstract fun favoriteImagesDao() : ImagesDao

    private class FavoriteImagesDbCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    populateDb(it.favoriteImagesDao())
                }
            }
        }
        suspend fun populateDb(imagesDao: ImagesDao) {
            imagesDao.deleteAll()
        }
    }



    companion object {
        @Volatile
        private var INSTANCE : FavoriteImagesDB? = null
        private val DB_NAME = "FavoriteImagesDb"

        fun getDatabase(context: Context , scope: CoroutineScope) : FavoriteImagesDB? {
            if (INSTANCE == null) {
                synchronized(FavoriteImagesDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext , FavoriteImagesDB::class.java , DB_NAME
                        ).addCallback(FavoriteImagesDbCallback(scope))
                            .build()
                    }
                }
            }
            return INSTANCE
        }

    }



    }