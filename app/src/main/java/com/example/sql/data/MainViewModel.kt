package com.example.sql.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//class MainViewModel(application: Apllication): ViewModel() {
class MainViewModel(application: Application): AndroidViewModel(application) {

    val lerTodosOsDados: LiveData<List<Heroi>>
    private val repository: UserRepository
    var itemSelecionado: Heroi? = null

    init {
        val userDao = UserDataBase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        lerTodosOsDados = repository.lerTodosOsDados
    }

    fun addUser(heroi: Heroi){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(heroi)
        }
    }

    fun updateUser(heroi: Heroi){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateUser(heroi)
        }
    }

    fun deleteUser(heroi: Heroi){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteUser(heroi)
        }
    }

}