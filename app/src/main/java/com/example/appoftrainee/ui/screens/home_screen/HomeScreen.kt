package com.example.appoftrainee.ui.screens.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.appoftrainee.R
import com.example.appoftrainee.data.User
import com.example.appoftrainee.ui.utils.FakeUser


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    clickToDetails: (String) -> Unit = {},
    viewModel: HomeScreenViewModel = viewModel()
) {
    val lazyColumnState = rememberLazyListState()
    val userList by viewModel.getUsersList().collectAsState(initial = emptyList())

    val dividerModifier = Modifier.padding(
        start = dimensionResource(R.dimen.home_screen_persons_list_items_photo_size)
                + dimensionResource(R.dimen.home_screen_persons_list_items_padding_horizontal)
                + dimensionResource(R.dimen.home_screen_persons_list_items_photo_padding_end),
        end = dimensionResource(R.dimen.home_screen_persons_list_items_padding_horizontal),
        top = dimensionResource(R.dimen.home_screen_persons_list_items_divider_padding_top),
        bottom = dimensionResource(R.dimen.home_screen_persons_list_items_divider_padding_bottom)
    )
    val spacerModifier = Modifier.height(dimensionResource(R.dimen.home_screen_persons_list_items_spacer_height))

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = lazyColumnState
    ) {
        itemsIndexed(
            items = userList,
            key = { _, user -> user.id }
        ) { index, user ->
            if (index == 0) {
                Spacer(modifier = spacerModifier)
            }
            PersonCardItem(
                user = user,
                clickToDetails = clickToDetails
            )
            if (index < userList.size - 1) {
                HorizontalDivider(modifier = dividerModifier)
            }
            if (index == userList.size - 1) {
                Spacer(modifier = spacerModifier)
            }
        }
    }
}

@Composable
fun PersonCardItem(
    modifier: Modifier = Modifier,
    user: User,
    clickToDetails: (String) -> Unit
) {
    Row(
        modifier = modifier
            .clickable { clickToDetails(user.id) }
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.home_screen_persons_list_items_padding_horizontal)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.home_screen_persons_list_items_photo_padding_end))
                .size(dimensionResource(R.dimen.home_screen_persons_list_items_photo_size))
                .clip(CircleShape),
            model = user.photoMedium,
            contentDescription = null
        )
        Column {
            Text(
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.home_screen_persons_list_items_name_padding_top),
                    bottom = dimensionResource(R.dimen.home_screen_persons_list_items_name_padding_bottom)
                ),
                text = "${user.firstName} ${user.lastName}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Address: ${user.country}, ${user.city}, ${user.streetName} ${user.streetNumber}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.home_screen_persons_list_items_telephone_padding_top)
                ),
                text = "Telephone: ${user.mobilePhone}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@[Composable Preview]
fun PersonCardItemPreview() {
    Surface {
        PersonCardItem(user = FakeUser(), clickToDetails = {})
    }
}