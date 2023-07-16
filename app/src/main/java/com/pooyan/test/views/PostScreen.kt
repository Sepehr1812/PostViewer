package com.pooyan.test.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.pooyan.test.R
import com.pooyan.test.data.models.Comment
import com.pooyan.test.data.models.Post
import com.pooyan.test.viewmodels.PostViewModel

@Composable
fun PostScreen(postId: Int) {
    val viewModel = hiltViewModel<PostViewModel>()
    LaunchedEffect(Unit) {
        viewModel.getPost(postId)
    }

    viewModel.postResponse?.also { post ->
        val pagingItems = viewModel.getComments(postId).collectAsLazyPagingItems()

        Surface {
            PostDetails(
                post = post,
                pagingItems = pagingItems,
                onLikeClick = {
                    viewModel.updateLikes(postId, it)
                    if (it != post.likes) viewModel.updateIsLiked(postId, it > post.likes)
                },
                onCommentSent = { content, newCommentCount ->
                    viewModel.updateComments(postId, newCommentCount)
                    viewModel.insertComment(
                        Comment(
                            id = 0,
                            user = "me",
                            content = content,
                            postId = postId
                        )
                    )
                    pagingItems.refresh()
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PostDetails(
    post: Post,
    pagingItems: LazyPagingItems<Comment>,
    onLikeClick: (Int) -> Unit,
    onCommentSent: (String, Int) -> Unit
) {
    val context = LocalContext.current

    Column(modifier = Modifier.padding(all = 8.dp)) {
        // username
        Text(text = post.user, style = MaterialTheme.typography.headlineMedium)

        // picture
        Spacer(modifier = Modifier.height(4.dp))
        Image(
            painter = painterResource(post.image),
            contentDescription = "Post Picture",
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .clip(RectangleShape)
        )

        // likes
        Spacer(modifier = Modifier.height(16.dp))
        val likedState = remember { mutableStateOf(post.isLiked) }
        LikeIcon(
            isLiked = post.isLiked,
            likes = post.likes,
            likedState = likedState,
            onLikeClick = onLikeClick
        )

        // comments
        Spacer(modifier = Modifier.height(4.dp))
        var commentCount by remember { mutableStateOf(post.comments) }
        Text(
            text = pluralStringResource(
                id = R.plurals.comments,
                count = commentCount,
                commentCount
            ),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 12.dp)
        )

        // cation
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = post.caption, style = MaterialTheme.typography.bodyMedium)

        // comments
        Spacer(modifier = Modifier.height(8.dp))
        Divider(Modifier.fillMaxWidth())
        Text(text = "Comments", style = MaterialTheme.typography.titleLarge)

        LazyColumn(modifier = Modifier.height(192.dp)) {

            items(
                items = pagingItems,
                key = { it.id }
            ) {
                it?.also { comment ->
                    CommentItem(comment = comment)
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
                                text = stringResource(R.string.loading_comments)
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
                            Text(text = stringResource(R.string.loading_comments))

                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }

                else -> {}
            }
        }

        // comment text field
        Spacer(modifier = Modifier.height(16.dp))

        var text by remember { mutableStateOf(TextFieldValue("")) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.type_your_comment),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = {
                    commentCount = commentCount.plus(1)
                    onCommentSent(text.text, commentCount)
                    text = TextFieldValue("")
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = stringResource(R.string.send))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Row(Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
        Text(
            text = comment.user,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = comment.content, style = MaterialTheme.typography.bodyMedium)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CommentItemPreview() {
//    PooyanTestTheme {
//        CommentItem(
//            Comment(
//                0,
//                "sepi",
//                "that is true",
//                0
//            )
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun CommentDetailsPreview() {
//    PooyanTestTheme {
//        Surface {
//            PostDetails(
//                Post(
//                    0,
//                    "sepehr",
//                    "Hi everyone!",
//                    25,
//                    1,
//                    R.drawable.profile_bg_men,
//                    false
//                ),
//                null,
//                {}
//            )
//        }
//    }
//}