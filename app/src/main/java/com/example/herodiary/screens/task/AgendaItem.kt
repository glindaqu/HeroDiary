package com.example.herodiary.screens.task


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.herodiary.database.room.models.TaskRoomModel
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("SimpleDateFormat")
@Composable
fun AgendaTaskItem(task: TaskRoomModel) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = task.title.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = task.description.toString()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (task.deadline != null) SimpleDateFormat("MMMM, dd", Locale.ENGLISH).format(task.deadline) else "null",
                    color = Color.Black.copy(0.7f)
                )
                Checkbox(checked = task.done?:false, onCheckedChange = {}, enabled = false)
            }
        }
    }
}