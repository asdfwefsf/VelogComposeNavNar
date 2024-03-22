package com.company.velogcomposenavnar

import android.app.FragmentManager.BackStackEntry
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.company.velogcomposenavnar.Screen.Screens.CallScreen
import com.company.velogcomposenavnar.Screen.Screens.HomeScreen
import com.company.velogcomposenavnar.Screen.Screen
import com.company.velogcomposenavnar.Screen.Screens.BottongScreen1
import com.company.velogcomposenavnar.Screen.Screens.BottongScreen2
import com.company.velogcomposenavnar.Screen.Screens.SettingsScreen
import com.company.velogcomposenavnar.ui.theme.VelogComposeNavNarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VelogComposeNavNarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNav()
                }
            }
        }
    }
}

// BottomNavBar
// 정의 : 앱에 보이는 화면의 최하단의 아이콘을 클릭하면 내가 원하는 화면으로 이동 할 수 있다.
// 특이사항 : 아이콘과 글자를 결합해서 아이콘 위에 숫자를 표시 할 수 있고(예를 들어 , 알림의 갯수 등등) 아이콘 밑에 어느 화면으로 이동 할 것인지에 대한 부연 설명도 쌉 가능하다.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav() {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val bottomNavVisibleRoutes = listOf(Screen.HomeScreen.route, Screen.CallScreen.route, Screen.SettingsScreen.route)


    val items = listOf(
        BottomNavItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            alarm = false
        ),
        BottomNavItem(
            title = "Call",
            selectedIcon = Icons.Filled.Call,
            unselectedIcon = Icons.Outlined.Call,
            alarm = false,
            badgeCount = 77
        ),
        BottomNavItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            alarm = true
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavVisibleRoutes) {
                NavigationBar {
                    items.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(bottomNavItem.title) {
                                    launchSingleTop = true
                                }
                            },
                            label = {
                                Text(text = bottomNavItem.title)
                            },
                            alwaysShowLabel = false,
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if(bottomNavItem.badgeCount != null) {
                                            Badge {
                                                Text(text = bottomNavItem.badgeCount.toString())
                                            }
                                        } else if(bottomNavItem.alarm) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            bottomNavItem.selectedIcon
                                        } else {
                                            bottomNavItem.unselectedIcon
                                        },
                                        contentDescription = bottomNavItem.title
                                    )
                                }
                            })
                    }
                }
            }

        }
    ) {
        it
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(navController)
            }
            composable(route = Screen.CallScreen.route) {
                CallScreen()
            }
            composable(route = Screen.SettingsScreen.route) {
                SettingsScreen()
            }
            composable(route = Screen.botongScreen1.route) {
                BottongScreen1()
            }
            composable(route = Screen.botongScreen2.route) {
                BottongScreen2()
            }
        }


    }
}