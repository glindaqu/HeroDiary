package com.example.herodiary.screens.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.herodiary.ui.theme.blue1

@Composable
fun EssentialCalendarNumberItem(text: String, isSelected: Boolean, clickable: () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .size(50.dp)
            .clickable { clickable() }
            .background(
                color = if (isSelected) blue1 else Color.Transparent,
                shape = RoundedCornerShape(15.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        EssentialCalendarText(it = text, selected = isSelected)
    }
}

@Composable
fun EssentialCalendarNumberPlaceholder() {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .size(50.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(15.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        EssentialCalendarText(it = "1", color = Color.White)
    }
}