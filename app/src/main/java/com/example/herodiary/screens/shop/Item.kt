package com.example.herodiary.screens.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.herodiary.R
import com.example.herodiary.database.room.models.ShopRoomModel

@Composable
fun ShopItem(shopRoomModel: ShopRoomModel) {
    Column(modifier = Modifier.wrapContentWidth().padding(horizontal = 10.dp)) {
        Image(
            painter = painterResource(id = shopRoomModel.drawable!!),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
        )
        Text(
            text = shopRoomModel.cost.toString() + "$",
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.End,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun Test_ShopItem() {
    ShopItem(shopRoomModel = ShopRoomModel(1, 100, R.drawable.ava1))
}