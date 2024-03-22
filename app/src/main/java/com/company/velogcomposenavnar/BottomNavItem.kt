package com.company.velogcomposenavnar

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem (
    val title : String,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector,
    val alarm : Boolean,
    val badgeCount : Int? = null
)