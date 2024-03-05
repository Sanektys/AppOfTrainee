package com.example.appoftrainee.ui.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appoftrainee.App
import com.example.appoftrainee.data.User
import com.example.appoftrainee.data.db.entities.UserProfile
import com.example.appoftrainee.domain.interactors.LocalDbInteractor
import com.example.appoftrainee.domain.interactors.RandomUserApiInteractor
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeScreenViewModel : ViewModel() {

    @Inject
    lateinit var randomUserApiInteractor: RandomUserApiInteractor
    @Inject
    lateinit var localDbInteractor: LocalDbInteractor

    private val _isDownloadFailed = MutableStateFlow(false)
    val isDownloadFailed = _isDownloadFailed.asStateFlow()


    init {
        App.instance.appComponent.inject(this)

        viewModelScope.launch {
            if (checkLocalDbUserListIsEmpty()) {
                downloadUserList()
            }
        }
    }


    fun getUsersList() = localDbInteractor.getLocalDbObserver().distinctUntilChanged()

    fun downloadUserList() {
        viewModelScope.launch {
            var newUsers = emptyList<UserProfile>()

            randomUserApiInteractor.getUsersFromApi()
                .catch {
                    _isDownloadFailed.value = true
                }
                .collect { users ->
                    newUsers = users

                    _isDownloadFailed.value = false
                }

            if (newUsers.isNotEmpty()) {
                localDbInteractor.changeListOfUsers(newUsers)
            }
        }
    }

    private suspend fun checkLocalDbUserListIsEmpty(): Boolean {
        var localUserList: List<User> = emptyList()

        val job = viewModelScope.launch {
            localDbInteractor.getLocalDbObserver().collect { dbList ->
                localUserList = dbList
                cancel()
            }
        }
        job.join()

        return localUserList.isEmpty()
    }
}