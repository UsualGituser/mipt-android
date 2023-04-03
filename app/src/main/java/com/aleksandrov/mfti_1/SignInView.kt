package com.aleksandrov.mfti_1

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
data class SignInViewState(
    var textPassword: String = "",
    var textEmail: String = "",
    var textUname: String = ""
)
sealed class SignInEvent {
    data class changePassword(val password: String): SignInEvent()
    data class changeEmail(val email: String) : SignInEvent()
    data class chanfeUname(val username: String) : SignInEvent()
}

class SignInView : ViewModel() {
    val viewState = MutableStateFlow(SignInViewState())

    fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.changePassword -> changePassFun(event.password)
            else -> {}
        }
    }
    private fun changePassFun(password: String) {
        viewState.value.textPassword = password
    }
}