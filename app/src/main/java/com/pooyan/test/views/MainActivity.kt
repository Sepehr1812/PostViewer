package com.pooyan.test.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pooyan.test.ui.theme.PooyanTestTheme
import com.pooyan.test.utils.DataUtil
import com.pooyan.test.utils.Routes.POST_LIST_SCREEN
import com.pooyan.test.utils.Routes.POST_SCREEN
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
                    composable(POST_SCREEN) {
                        PostScreen(navController)
                    }
                }
            }
        }
    }
}
