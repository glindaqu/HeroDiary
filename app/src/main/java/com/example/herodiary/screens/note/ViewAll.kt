package com.example.herodiary.screens.note

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.herodiary.Routes
import com.example.herodiary.state.NoteScreenStates
import com.example.herodiary.ui.theme.blue1
import com.example.herodiary.viewModels.impl.NoteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewAll(viewModel: NoteViewModel, navHostController: NavHostController) {
    val notes by viewModel.getAll().collectAsState(initial = listOf())
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.setUiState(NoteScreenStates.CREATE) },
                containerColor = Color.White,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        containerColor = blue1
    ) {
        LazyColumn {
            item {
                Text(
                    text = "Notes",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 30.dp)
                        .padding(start = 10.dp)
                )
            }
            items(notes) {
                Item(it) {
                    navHostController.navigate(Routes.NOTES_EDIT.title + "/" + it.id.toString())
                }
            }
        }
    }
}