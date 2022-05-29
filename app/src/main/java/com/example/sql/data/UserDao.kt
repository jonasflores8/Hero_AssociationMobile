package com.example.sql.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(heroi: Heroi)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun lerTodosOsDados(): LiveData<List<Heroi>>

    @Delete
    fun deleteUser(heroi: Heroi)

    @Update
    fun updateUser(heroi: Heroi)

}