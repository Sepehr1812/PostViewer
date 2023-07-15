package com.pooyan.test.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pooyan.test.ui.theme.PooyanTestTheme
import com.pooyan.test.utils.Routes.POST_LIST_SCREEN
import com.pooyan.test.utils.Routes.POST_SCREEN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PooyanTestTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = POST_LIST_SCREEN) {
                    composable(POST_LIST_SCREEN) {
                        PostListScreen(navController)
                    }
                    composable(POST_SCREEN) {
                        PostScreen(navController)
                    }
                }
            }
        }
    }
}
