package com.example.herodiary.screens.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.herodiary.database.room.models.NoteRoomModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun Item(note: NoteRoomModel, openCallback: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .clickable { openCallback() },
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = note.title.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = note.content.toString())
            Text(
                text = SimpleDateFormat("MMMM, dd", Locale.ENGLISH).format(note.creationDate),
                color = Color.Black.copy(0.7f),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}