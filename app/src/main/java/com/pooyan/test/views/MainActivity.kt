package com.pooyan.test.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pooyan.test.ui.theme.PooyanTestTheme
import com.pooyan.test.utils.DataUtil
import com.pooyan.test.utils.Routes.POST_LIST_SCREEN
import com.pooyan.test.utils.Routes.POST_SCREEN
import com.pooyan.test.utils.Routes.Values.POST_SCREEN_VALUE
import com.pooyan.test.viewmodels.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // initialize data
            val usernameList = DataUtil.generateUsernames()
            val commentList = DataUtil.generateSampleCommentData(usernameList)
            val postList = DataUtil.generateSamplePostData(usernameList, commentList)

            val postListViewModel = hiltViewModel<PostListViewModel>()
            postListViewModel.insertPosts(postList)
            postListViewModel.insertCommentList(commentList)

            PooyanTestTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = POST_LIST_SCREEN) {
                    composable(POST_LIST_SCREEN) {
                        PostListScreen(navController, postListViewModel)
                    }
                    composable(
                        POST_SCREEN,
                        arguments = listOf(navArgument(POST_SCREEN_VALUE) {
                            type = NavType.IntType
                        })
                    ) {
                        PostScreen(
                            it.arguments?.getInt(POST_SCREEN_VALUE, 0) ?: 0
                        )
                    }
                }
            }
        }
    }
}
