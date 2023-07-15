package com.pooyan.test.views

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pooyan.test.R
import com.pooyan.test.data.models.Post
import com.pooyan.test.ui.theme.PooyanTestTheme

@Composable
fun PostListScreen(navController: NavController?) {

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PostCard(post: Post) {
    Column(modifier = Modifier.padding(all = 8.dp)) {
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
        Spacer(modifier = Modifier.width(8.dp))
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
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PostListScreenPreview() {
    PooyanTestTheme {
        Surface {
            PostCard(
                Post(
                    0,
                    "sepehr",
                    "Hi everyone!",
                    25,
                    1,
                    R.drawable.profile_bg_men
                )
            )
        }
    }
}