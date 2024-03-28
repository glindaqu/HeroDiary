package com.example.herodiary.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.herodiary.R
import com.example.herodiary.state.EmailStates
import com.example.herodiary.state.LoginStates
import com.example.herodiary.state.PasswordAgainStates
import com.example.herodiary.state.PasswordStates
import com.example.herodiary.viewModels.impl.LoginViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Login(viewModel: LoginViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    val systemUiController = rememberSystemUiController()
    val bg = MaterialTheme.colorScheme.background
    LaunchedEffect(Unit) { systemUiController.setSystemBarsColor(bg) }
    when (uiState.value) {
        LoginStates.INPUT -> LoginInput(viewModel = viewModel)
        LoginStates.REQUEST -> Loading()
        LoginStates.LOGIN_FAILED -> Error(viewModel)
        LoginStates.REGISTRATION_FAILED -> Error(viewModel)
        LoginStates.SUCCESS -> viewModel.loadProfile()
    }
}

@Composable
fun LoginInput(viewModel: LoginViewModel) {
    var showRegistration by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)
    ) {
        Text(
            text = "HERO'S DIARY",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        when (showRegistration) {
            true -> Registration(viewModel = viewModel)
            else -> SignIn(viewModel = viewModel)
        }
        TextButton(onClick = { showRegistration = !showRegistration }) {
            Text(
                text = if (!showRegistration) "Not have an account?\nRegistration" else "Already registered?\nLogin",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Registration(viewModel: LoginViewModel) {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordAgain by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var haveEmailError by remember { mutableStateOf(false) }
    var havePasswordError by remember { mutableStateOf(false) }
    var haveCompareError by remember { mutableStateOf(false) }
    var passwordLabel by remember { mutableStateOf(PasswordStates.OK) }
    var passwordAgainLabel by remember { mutableStateOf(PasswordAgainStates.OK) }
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
            isError = haveEmailError,
            keyboardActions = KeyboardActions {
                haveEmailError = viewModel.validateEmail(email.text)
                if (haveEmailError) emailLabel = EmailStates.ERROR
            },
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it; havePasswordError = false; passwordLabel = PasswordStates.OK
            },
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
            isError = havePasswordError,
            keyboardActions = KeyboardActions {
                havePasswordError = viewModel.validatePassword(password.text)
                if (havePasswordError) passwordLabel = PasswordStates.ERROR
            },
            singleLine = true
        )
        OutlinedTextField(
            value = passwordAgain,
            onValueChange = {
                passwordAgain = it; haveCompareError = false; passwordAgainLabel =
                PasswordAgainStates.OK
            },
            modifier = Modifier
                .fillMaxWidth()
                .semantics { if (haveCompareError) error("Password are not matches") }
                .onFocusChanged {
                    if (!it.isFocused && passwordAgain.text.isNotEmpty()) haveCompareError =
                        viewModel.comparePasswords(password.text, passwordAgain.text)
                    if (haveCompareError) passwordAgainLabel = PasswordAgainStates.ERROR
                },
            placeholder = { Text(passwordAgainLabel) },
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
            label = { Text("Password again") },
            isError = haveCompareError,
            keyboardActions = KeyboardActions {
                haveCompareError = viewModel.comparePasswords(password.text, passwordAgain.text)
                if (haveCompareError) passwordAgainLabel = PasswordAgainStates.ERROR
            },
            singleLine = true
        )
        Button(
            onClick = { viewModel.register(password.text, email.text) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(
                text = "Registration",
                fontSize = 18.sp
            )
        }
    }
}

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

@Composable
fun Loading() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.load))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(100.dp),
            iterations = LottieConstants.IterateForever
        )
    }
}

@Composable
fun Error(viewModel: LoginViewModel) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(400.dp),
            iterations = LottieConstants.IterateForever
        )
        OutlinedButton(
            onClick = { viewModel.updateUiState(LoginStates.INPUT) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "Try again",
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }
}