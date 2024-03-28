package com.example.herodiary.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.herodiary.viewModels.impl.LoginViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Login(viewModel: LoginViewModel) {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordAgain by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var showRegistration by remember { mutableStateOf(false) }
    var haveEmailError by remember { mutableStateOf(false) }
    var havePasswordError by remember { mutableStateOf(false) }
    var haveCompareError by remember { mutableStateOf(false) }
    val systemUiController = rememberSystemUiController()
    val bg = MaterialTheme.colorScheme.background
    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(bg)
    }
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
            true -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(horizontal = 25.dp)
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it; haveEmailError = false },
                        modifier = Modifier.fillMaxWidth().semantics { if (haveEmailError) error("Email must contain @ and . symbols") },
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
                        label = { Text("Your email") },
                        isError = haveEmailError,
                        keyboardActions = KeyboardActions { haveEmailError = viewModel.validateEmail(email.text) },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it; havePasswordError = false },
                        modifier = Modifier.fillMaxWidth().semantics {  if (havePasswordError) error("Password must be 6+ characters in length") },
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
                        label = { Text("Your password") },
                        isError = havePasswordError,
                        keyboardActions = KeyboardActions { havePasswordError = viewModel.validatePassword(password.text) },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = passwordAgain,
                        onValueChange = { passwordAgain = it; haveCompareError = false },
                        modifier = Modifier.fillMaxWidth().semantics { if (haveCompareError) error("Password are not matches") },
                        placeholder = { Text("Password again") },
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
                        keyboardActions = KeyboardActions { haveCompareError = viewModel.comparePasswords(password.text, passwordAgain.text) },
                        singleLine = true
                    )
                    Button(
                        onClick = { viewModel.login(password.text, email.text) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Text(
                            text = "Registration",
                            fontSize = 18.sp
                        )
                    }
                }
            } else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(horizontal = 25.dp)
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth().semantics { if (haveEmailError) error("Email must contain @ and . symbols") },
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
                        label = { Text("Your email") },
                        keyboardActions = KeyboardActions { haveEmailError = viewModel.validateEmail(email.text) },
                        isError = haveEmailError,
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth().semantics { if (havePasswordError) error("Password must be 6+ characters in length") },
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
                        label = { Text("Your password") },
                        keyboardActions = KeyboardActions { havePasswordError = viewModel.validatePassword(password.text) },
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
        }
        TextButton(onClick = { showRegistration = !showRegistration }) {
            Text(
                text = if (!showRegistration) "Not have an account?\nRegistration" else "Already registered?\nLogin",
                textAlign = TextAlign.Center
            )
        }
    }
}