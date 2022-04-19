package example.task64.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.task64.data.model.ImageModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {
    @Query("SELECT * FROM favorite_images")
     fun getAllImages(): Flow<List<ImageModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(image : ImageModel)

    @Query("DELETE FROM  favorite_images")
   suspend fun deleteAll()

}