package com.aleksandrov.mfti_1

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SignInViewState(
    var textPassword: String = "",
    var textEmail: String = "",
    var textUname: String = "",
    var passwordVisible: Boolean = false,
    var emailColor: Color = Color.Gray,
    var keepSignedIn: Color = Color.Gray
)
sealed class SignInEvent {
    data class changePassword(val password: String): SignInEvent()
    data class changeEmail(val email: String) : SignInEvent()
    data class changeUname(val username: String) : SignInEvent()
    object passVisible : SignInEvent()
    object changeEmailColor: SignInEvent()
    object changeSignedInColor: SignInEvent()
}

class SignInView : ViewModel() {
    private val _viewState = MutableStateFlow(SignInViewState())
    val viewState: StateFlow<SignInViewState> = _viewState.asStateFlow()
    fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.changeEmail -> changeEmailFun(event.email)
            is SignInEvent.changePassword -> changePassFun(event.password)
            is SignInEvent.changeUname -> changeUnameFun(event.username)
            is SignInEvent.passVisible -> passVisibleFun()
            is SignInEvent.changeEmailColor -> changeColorEmail()
            is SignInEvent.changeSignedInColor -> changeColorSignIn()
        }
    }

    private fun changeUnameFun(uname: String) = viewModelScope.launch {
        _viewState.update { currentState ->
            currentState.copy(textUname = uname)
        }
    }

    private fun changeEmailFun(email: String) = viewModelScope.launch {
        _viewState.update { currentState ->
            currentState.copy(textEmail = email)
        }
    }

    private fun changePassFun(password: String) = viewModelScope.launch {
        _viewState.update { currentState ->
            currentState.copy(textPassword = password)
        }
    }

    private fun passVisibleFun() = viewModelScope.launch {
        _viewState.update { currentState ->
            currentState.copy(passwordVisible = !viewState.value.passwordVisible)
        }
    }

    private fun changeColorEmail() = viewModelScope.launch {
        if (viewState.value.emailColor == Color.Gray) {
            _viewState.update { currentState ->
                currentState.copy(emailColor = Color.Green)
            }
        } else {
            _viewState.update { currentState ->
                currentState.copy(emailColor = Color.Gray)
            }
        }
    }

    private fun changeColorSignIn() = viewModelScope.launch {
        if (viewState.value.keepSignedIn == Color.Gray) {
            _viewState.update { currentState ->
                currentState.copy(keepSignedIn = Color.Green)
            }
        } else {
            _viewState.update { currentState ->
                currentState.copy(keepSignedIn = Color.Gray)
            }
        }
    }
}