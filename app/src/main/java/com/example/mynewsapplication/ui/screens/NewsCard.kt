package com.example.mynewsapplication.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.mynewsapplication.model.Article

@Composable
fun NewsCard(article: Article) {
    val context = LocalContext.current
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier
            .padding(16.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                context.startActivity(intent)
            }) {
            article.title?.let { Text(text = it, style = MaterialTheme.typography.h6) }

            article.urlToImage?.let { imageUrl ->
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl,
                        builder = {
                            transformations(RoundedCornersTransformation(8f))
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Text(text = article.description ?: "", style = MaterialTheme.typography.body2)
        }
    }
}