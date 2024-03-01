package com.example.appoftrainee.ui.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.example.appoftrainee.R


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val lazyColumnState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = lazyColumnState
    ) {
        items(32) {index ->
            if (index == 0) {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.home_screen_persons_list_items_spacer_height)))
            }
            PersonCardItem()
            if (index < 32) {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.home_screen_persons_list_items_spacer_height)))
            }
        }
    }
}

@Composable
fun PersonCardItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = dimensionResource(R.dimen.home_screen_persons_list_items_padding_horizontal)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.home_screen_persons_list_items_photo_padding_end))
                .size(dimensionResource(R.dimen.home_screen_persons_list_items_photo_size))
                .clip(CircleShape),
            model = "https://randomuser.me/api/portraits/med/men/96.jpg",
            contentDescription = null
        )
        Column {
            Text(
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.home_screen_persons_list_items_name_padding_bottom)),
                text = "Shesterov Ilya Andreevich",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Address: Marino 110/800, apr 29",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Telephone: 8 800 555 35 35",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}