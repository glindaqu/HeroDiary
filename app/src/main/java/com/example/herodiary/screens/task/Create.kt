package com.example.herodiary.screens.task

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.herodiary.database.room.models.TaskRoomModel
import com.example.herodiary.notify.checkNotificationPermissions
import com.example.herodiary.notify.scheduleNotification
import com.example.herodiary.state.TaskScreenStates
import com.example.herodiary.ui.theme.blue1
import com.example.herodiary.viewModels.impl.TaskViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("SimpleDateFormat", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Create(email: String, viewModel: TaskViewModel) {
    var titleState by remember { mutableStateOf(TextFieldValue("")) }
    var descriptionState by remember { mutableStateOf(TextFieldValue("")) }
    val datePickerState = rememberSheetState()
    var textState by remember { mutableStateOf("Select date") }
    var selectedDate by remember { mutableLongStateOf(System.currentTimeMillis()) }
    val context = LocalContext.current
    CalendarDialog(
        state = datePickerState,
        selection = CalendarSelection.Date { localDate ->
            val stringDate = "${localDate.monthValue}, ${localDate.dayOfMonth} ${localDate.year}"
            textState = "${
                localDate.month.toString().lowercase(Locale.ROOT)
                    .replaceFirstChar { it.uppercase() }
            }, ${localDate.dayOfMonth} ${localDate.year}"
            selectedDate = SimpleDateFormat("M, dd yyyy").parse(stringDate)?.time ?: 0
        },
        config = CalendarConfig(
            CalendarStyle.MONTH,
            monthSelection = true,
            yearSelection = true
        ),
    )
    Scaffold(
        containerColor = blue1,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val model = TaskRoomModel(
                        null,
                        titleState.text,
                        false,
                        selectedDate,
                        descriptionState.text,
                        email
                    )
                    if (checkNotificationPermissions(context)) {
                        viewModel.add(model)
                        scheduleNotification(context, "Whoa! There is deadline!", "The task ${model.title} ends today! Hurry up!", Date(selectedDate))
                        viewModel.updateUiState(TaskScreenStates.VIEW)
                    }
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
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
            ) {
                IconButton(onClick = { viewModel.updateUiState(TaskScreenStates.VIEW) }) {
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
                placeholder = { Text("Description") },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Button(
                onClick = { datePickerState.show() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = textState,
                    fontSize = 18.sp
                )
            }
        }
    }
}
