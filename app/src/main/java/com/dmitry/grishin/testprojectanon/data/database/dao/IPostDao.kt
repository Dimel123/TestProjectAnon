package com.dmitry.grishin.testprojectanon.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dmitry.grishin.testprojectanon.data.database.entities.PostEntity
import io.reactivex.Flowable

@Dao
interface IPostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<PostEntity>)

    @Query("SELECT * FROM PostEntity ORDER BY id DESC LIMIT :limit OFFSET :offset")
    fun getListEntities(limit: Int, offset: Int): List<PostEntity>

}