package com.programacionymas.drivingevaluation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.programacionymas.drivingevaluation.data.UserRepository
import com.programacionymas.drivingevaluation.ui.Screen
import com.programacionymas.drivingevaluation.util.Event

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _navigateTo = MutableLiveData<Event<Screen>>()
    val navigateTo: LiveData<Event<Screen>>
        get() = _navigateTo

    /**
     * Consider all sign ins successful
     */
    fun signIn(email: String, password: String) {
        userRepository.signIn(email, password)
        _navigateTo.value = Event(Screen.DriversList)
    }

    fun signUp() {
        // Open the registration form in web
    }
}

class SignInViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}