package com.pooyan.test.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.pooyan.test.R
import com.pooyan.test.data.models.Post
import com.pooyan.test.utils.Routes
import com.pooyan.test.viewmodels.PostListViewModel

@Composable
fun PostListScreen(navController: NavController, viewModel: PostListViewModel) {

    val pagingItems = viewModel.getPosts().collectAsLazyPagingItems()

    Surface {
        PostList(
            pagingItems = pagingItems,
            onCardClick = { navController.navigate(Routes.getPostScreenPath(it)) })
    }
}

@Composable
fun PostList(pagingItems: LazyPagingItems<Post>, onCardClick: (Int) -> Unit) {
    val context = LocalContext.current

    LazyColumn {

        items(
            items = pagingItems,
            key = { it.id }
        ) {
            it?.also { post ->
                PostCard(post = post, onCardClick = onCardClick)
            }
        }

        when (val state = pagingItems.loadState.refresh) { // FIRST LOAD
            is LoadState.Error ->
                Toast.makeText(context, "Error: ${state.error}", Toast.LENGTH_SHORT).show()

            is LoadState.Loading -> { // Loading UI
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = stringResource(R.string.loading_posts)
                        )

                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            else -> {}
        }

        when (val state = pagingItems.loadState.append) { // Pagination
            is LoadState.Error ->
                Toast.makeText(context, "Error: ${state.error}", Toast.LENGTH_SHORT).show()

            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom,
                    ) {
                        Text(text = stringResource(R.string.loading_posts))

                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            else -> {}
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PostCard(post: Post, onCardClick: (Int) -> Unit) {
    Column(modifier = Modifier
        .padding(all = 8.dp)
        .clickable { onCardClick(post.id) }) {
        // username
        Text(text = post.user, style = MaterialTheme.typography.headlineSmall)

        // picture
        Spacer(modifier = Modifier.height(4.dp))
        Image(
            painter = painterResource(post.image),
            contentDescription = "Post Picture",
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(RectangleShape)
        )

        // likes
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = pluralStringResource(id = R.plurals.likes, count = post.likes, post.likes),
            style = MaterialTheme.typography.titleMedium
        )

        // comments
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = pluralStringResource(
                id = R.plurals.comments,
                count = post.comments,
                post.comments
            ),
            style = MaterialTheme.typography.titleMedium
        )

        // cation
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = post.caption, style = MaterialTheme.typography.bodyMedium)
    }

    Divider(Modifier.fillMaxWidth())
}

//@Preview(showBackground = true)
//@Composable
//fun PostListPreview() {
//    val usernameList = DataUtil.generateUsernames()
//    val commentList = DataUtil.generateSampleCommentData(usernameList)
//    val postList = DataUtil.generateSamplePostData(usernameList, commentList)
//
//    PooyanTestTheme {
//        PostList(pagingItems = postList) {}
//    }
//}

//@Preview(showBackground = true, name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//@Composable
//fun PostCardPreview() {
//    PooyanTestTheme {
//        Surface {
//            PostCard(
//                Post(
//                    0,
//                    "sepehr",
//                    "Hi everyone!",
//                    25,
//                    1,
//                    R.drawable.profile_bg_men
//                )
//            )
//        }
//    }
//}