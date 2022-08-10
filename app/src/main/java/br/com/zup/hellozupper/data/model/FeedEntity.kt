package br.com.zup.hellozupper.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed_table")
class FeedEntity(
    @PrimaryKey
    val id: Int,
)