package com.example.herodiary

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class Route(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    override fun toString(): String {
        return title
    }
}

object Routes {
    val TASK = Route("Tasks", Icons.Filled.Check, Icons.Outlined.Check)
    val PROFILE = Route("Profile", Icons.Filled.Person, Icons.Outlined.Person)
    val SHOP = Route("Shop", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart)

    val all = listOf(TASK, PROFILE, SHOP)
}