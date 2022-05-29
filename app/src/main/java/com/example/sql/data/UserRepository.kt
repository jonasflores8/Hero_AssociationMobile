package com.example.sql.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val lerTodosOsDados: LiveData<List<Heroi>> = userDao.lerTodosOsDados()

    fun addUser(heroi: Heroi){
        userDao.addUser(heroi)
    }

    fun updateUser(heroi: Heroi){
        userDao.updateUser(heroi)
    }

    fun deleteUser(heroi: Heroi){
        userDao.deleteUser(heroi)
    }
}