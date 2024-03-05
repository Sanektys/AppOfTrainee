package com.example.appoftrainee.ui.screens.details_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appoftrainee.App
import com.example.appoftrainee.data.User
import com.example.appoftrainee.domain.interactors.LocalDbInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailsScreenViewModel : ViewModel() {

    @Inject
    lateinit var localDbInteractor: LocalDbInteractor

    private val _userDataState: MutableState<User?> = mutableStateOf(null)
    val userDataState: State<User?> = _userDataState


    init {
        App.instance.appComponent.inject(this)
    }


    fun getUserFromLocalDbById(userId: String) {
        viewModelScope.launch {
            _userDataState.value = localDbInteractor.getUserById(userId)
        }
    }
}