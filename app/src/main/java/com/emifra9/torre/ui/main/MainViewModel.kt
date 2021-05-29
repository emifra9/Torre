package com.emifra9.torre.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.emifra9.torre.repo.TorreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    torreRepository: TorreRepository
)  : ViewModel() {
    init {
        viewModelScope.launch {
            torreRepository.refreshUser("emilianofraile9")
        }
    }

    val user = torreRepository.getUser("emilianofraile9").asLiveData()



}
