package com.emifra9.torre.ui.main

import androidx.lifecycle.*
import com.emifra9.torre.data.User
import com.emifra9.torre.repo.TorreRepository
import com.emifra9.torre.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val torreRepository: TorreRepository
)  : ViewModel() {




     fun getUser(publicId: String): LiveData<User> {
        return torreRepository.getUser(publicId).asLiveData()
    }

    fun getAllUsers() : LiveData<List<User>> {
        return  torreRepository.getAllUsers().asLiveData()
    }

    fun findUser(publicId: String): MutableLiveData<Resource<User>> {
        val user = MutableLiveData<Resource<User>>()
        viewModelScope.launch {
            user.postValue( torreRepository.getUserApi(publicId))
        }
        return user
    }

}
