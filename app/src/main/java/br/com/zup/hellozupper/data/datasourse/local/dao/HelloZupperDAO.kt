package br.com.zup.hellozupper.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.hellozupper.data.model.FeedEntity

@Dao
interface HelloZupperDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveReadNewsIndex(news: FeedEntity)

    @Query("SELECT * FROM feed_table")
    fun getAllReadNews(): List<FeedEntity>

    @Query("DELETE FROM feed_table WHERE id = :idNews")
    fun deleteNewsDB(idNews: Int)
}