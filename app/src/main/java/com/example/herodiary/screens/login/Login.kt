package com.example.herodiary.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.herodiary.state.EmailStates
import com.example.herodiary.state.PasswordStates
import com.example.herodiary.viewModels.impl.LoginViewModel


@Composable
fun SignIn(viewModel: LoginViewModel) {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var haveEmailError by remember { mutableStateOf(false) }
    var havePasswordError by remember { mutableStateOf(false) }
    var passwordLabel by remember { mutableStateOf(PasswordStates.OK) }
    var emailLabel by remember { mutableStateOf(EmailStates.OK) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 25.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it; haveEmailError = false; emailLabel = EmailStates.OK },
            modifier = Modifier
                .fillMaxWidth()
                .semantics { if (haveEmailError) error("Email must contain @ and . symbols") }
                .onFocusChanged {
                    if (!it.isFocused && email.text.isNotEmpty()) haveEmailError =
                        viewModel.validateEmail(email.text)
                    if (haveEmailError) emailLabel = EmailStates.ERROR
                },
            placeholder = { Text("Email") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            label = { Text(emailLabel) },
            keyboardActions = KeyboardActions {
                haveEmailError = viewModel.validateEmail(email.text)
                if (haveEmailError) emailLabel = EmailStates.ERROR
            },
            isError = haveEmailError,
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it; havePasswordError = false; passwordLabel = PasswordStates.OK },
            modifier = Modifier
                .fillMaxWidth()
                .semantics { if (havePasswordError) error("Password must be 6+ characters in length") }
                .onFocusChanged {
                    if (!it.isFocused && password.text.isNotEmpty()) havePasswordError =
                        viewModel.validatePassword(password.text)
                    if (havePasswordError) passwordLabel = PasswordStates.ERROR
                },
            placeholder = { Text("Password") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(passwordLabel) },
            keyboardActions = KeyboardActions {
                havePasswordError = viewModel.validatePassword(password.text)
                if (havePasswordError) passwordLabel = PasswordStates.ERROR
            },
            isError = havePasswordError,
            singleLine = true
        )
        Button(
            onClick = { viewModel.login(password.text, email.text) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(
                text = "Login",
                fontSize = 18.sp
            )
        }
    }
}