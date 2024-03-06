package com.example.appoftrainee.ui.screens.home_screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.appoftrainee.R
import com.example.appoftrainee.data.User
import com.example.appoftrainee.ui.theme.AppOfTraineeTheme
import com.example.appoftrainee.ui.utils.FakeUser
import java.util.Collections
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    clickToDetails: (String) -> Unit = {},
    snackbar: () -> SnackbarHostState,
    viewModel: HomeScreenViewModel = viewModel()
) {
    val userList by viewModel.getUsersList().collectAsState(initial = emptyList())
    val refreshState = rememberPullToRefreshState()

    if (refreshState.isRefreshing) {
        LaunchedEffect(true) {
            viewModel.downloadUserList(onCompleteAction = { refreshState.endRefresh() })
        }
    }

    val context = LocalContext.current
    if (viewModel.isDownloadFailed.value) {
        LaunchedEffect(true) {
            val result = snackbar().showSnackbar(
                message = context.getString(R.string.home_screen_snackbar_message),
                actionLabel = context.getString(R.string.home_screen_snackbar_action_label)
            )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    refreshState.startRefresh()
                }
                SnackbarResult.Dismissed -> {}
            }
        }
    }

    Box(modifier = modifier.nestedScroll(refreshState.nestedScrollConnection)) {
        UserList(userList = userList, clickToDetails = clickToDetails)

        PullToRefreshContainer(modifier = Modifier.align(Alignment.TopCenter), state = refreshState)
    }
}

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    userList: List<User>,
    clickToDetails: (String) -> Unit
) {
    val lazyColumnState = rememberLazyListState()

    val dividerModifier = Modifier.padding(
        start = dimensionResource(R.dimen.home_screen_persons_list_items_photo_size)
                + dimensionResource(R.dimen.home_screen_persons_list_items_padding_horizontal)
                + dimensionResource(R.dimen.home_screen_persons_list_items_photo_padding_end),
        end = dimensionResource(R.dimen.home_screen_persons_list_items_padding_horizontal),
        top = dimensionResource(R.dimen.home_screen_persons_list_items_divider_padding_top),
        bottom = dimensionResource(R.dimen.home_screen_persons_list_items_divider_padding_bottom)
    )
    val spacerModifier = Modifier.height(dimensionResource(R.dimen.home_screen_persons_list_items_spacer_height))
    val localView = LocalView.current

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = lazyColumnState
    ) {
        itemsIndexed(
            items = userList,
            key = { _, user ->
                if (!localView.isInEditMode) {
                    user.id
                } else {
                    Random.nextInt() // Фейковый ключ для превью чтобы оно работало
                }
            }
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


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
fun UserListPreview() {
    AppOfTraineeTheme {
        Surface {
            UserList(userList = Collections.nCopies(16, FakeUser), clickToDetails = {})
        }
    }
}