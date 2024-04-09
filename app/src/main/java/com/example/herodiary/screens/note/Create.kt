package com.example.herodiary.screens.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.herodiary.database.room.models.NoteRoomModel
import com.example.herodiary.state.NoteScreenStates
import com.example.herodiary.ui.theme.blue1
import com.example.herodiary.viewModels.impl.NoteViewModel

@Composable
fun Create(email: String, viewModel: NoteViewModel) {
    var titleState by remember { mutableStateOf(TextFieldValue("")) }
    var descriptionState by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        containerColor = blue1,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.insert(
                        NoteRoomModel(
                            null,
                            titleState.text,
                            descriptionState.text,
                            email,
                            System.currentTimeMillis()
                        )
                    )
                    viewModel.setUiState(NoteScreenStates.VIEW_ALL)
                },
                containerColor = Color.White,
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Left,
                modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 10.dp, vertical = 20.dp)
            ) {
                IconButton(onClick = { viewModel.setUiState(NoteScreenStates.VIEW_ALL) }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = null,
                    )
                }
                Text(
                    text = "Create",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            OutlinedTextField(
                value = titleState,
                onValueChange = { titleState = it },
                placeholder = { Text("Title") },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = descriptionState,
                onValueChange = { descriptionState = it },
                placeholder = { Text("Content") },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 7
            )
        }
    }
}