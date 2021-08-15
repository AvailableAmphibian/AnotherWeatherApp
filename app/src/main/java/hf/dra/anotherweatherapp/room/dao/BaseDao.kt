package hf.dra.anotherweatherapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<T> {
    @Insert
    fun insert(t:T)

    @Delete
    fun delete(t:T)
}