package com.example.herodiary.screens.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.herodiary.viewModels.impl.ShopViewModel

@Composable
fun ShopItem(shopRoomModel: ShopRoomModel, viewModel: ShopViewModel, isBought: Boolean, isCurrent: Boolean) {
    var textState by remember { mutableStateOf(shopRoomModel.cost.toString() + "$") }
    when {
        isCurrent -> textState = "In Use"
        isBought -> textState = "Set"
    }
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 10.dp)
            .clickable {
                viewModel.updateImage(shopRoomModel.drawable!!)
                if (!isBought) viewModel.buy(shopRoomModel.id!!, shopRoomModel.cost)
            }
    ) {
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
            text = textState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.End,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
