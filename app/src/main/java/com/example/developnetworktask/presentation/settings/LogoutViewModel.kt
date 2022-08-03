package com.example.developnetworktask.presentation.settings


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developnetworktask.data.repository.DataStoreManager

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(private val dataStore: DataStoreManager) : ViewModel() {


    fun onEvent(event: LogoutEvent) {
        when(event) {
            LogoutEvent.Logout -> {
              viewModelScope.launch {

                  dataStore.save("0")  }

            }
        }
    }


}