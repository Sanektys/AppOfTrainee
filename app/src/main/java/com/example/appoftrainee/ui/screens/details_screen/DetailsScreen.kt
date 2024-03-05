package com.example.appoftrainee.ui.screens.details_screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.appoftrainee.R
import com.example.appoftrainee.data.User
import com.example.appoftrainee.ui.utils.FakeUser


@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    personId: String,
    viewModel: DetailsScreenViewModel = viewModel()
) {
    LaunchedEffect(personId) {
        viewModel.getUserFromLocalDbById(personId)
    }

    val userData by viewModel.userDataState

    UserInfo(modifier = modifier, userData = userData)
}

@Composable
fun UserInfo(modifier: Modifier = Modifier, userData: User?) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(scrollState)
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.details_screen_photo_padding))
                .size(dimensionResource(R.dimen.details_screen_photo_size))
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally),
            model = userData?.photoLarge,
            contentDescription = stringResource(R.string.details_screen_photo_description),
            placeholder = painterResource(R.drawable.avatar_placeholder),
            error = painterResource(R.drawable.avatar_placeholder)
        )
        Text(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.details_screen_name_padding_bottom))
                .align(Alignment.CenterHorizontally),
            text = "${userData?.firstName} ${userData?.lastName}",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(
                R.string.details_screen_name,
                userData?.gender ?: "",
                userData?.age ?: 0,
                userData?.birthDate?.let { formatDate(it) } ?: ""
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(
                alpha = ResourcesCompat.getFloat(
                    LocalContext.current.resources,
                    R.dimen.details_screen_sex_age_alpha
                )
            )
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.details_screen_top_divider_padding_top),
                bottom = dimensionResource(R.dimen.details_screen_divider_padding_vertical)
            )
        )
        PersonsEmail(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.details_screen_field_padding_horizontal)),
            email = userData?.email ?: ""
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.details_screen_divider_padding_vertical)))
        PersonsTelephoneNumber(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.details_screen_field_padding_horizontal)),
            phoneTypeName = stringResource(R.string.details_screen_phone_home),
            telephone = userData?.homePhone ?: ""
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.details_screen_telephone_spacer_height)))
        PersonsTelephoneNumber(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.details_screen_field_padding_horizontal)),
            phoneTypeName = stringResource(R.string.details_screen_phone_mobile),
            telephone = userData?.mobilePhone ?: ""
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.details_screen_divider_padding_vertical)))
        PersonsAddress(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.details_screen_field_padding_horizontal)),
            country = userData?.country ?: "",
            state = userData?.state ?: "",
            city = userData?.city ?: "",
            street = "${userData?.streetName} ${userData?.streetNumber}",
            latitude = userData?.latitude ?: "",
            longitude = userData?.longitude ?: ""
        )
    }
}

@Composable
fun PersonsEmail(modifier: Modifier = Modifier, email: String) {
    val context = LocalContext.current
    val primaryColor = MaterialTheme.colorScheme.primary
    val emailString = remember(email) {
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                append(context.getString(R.string.details_screen_email) + ": ")
            }
            pushStringAnnotation(tag = EMAIL_ANNOTATION_TAG, annotation = email)
            withStyle(
                style = SpanStyle(
                    color = primaryColor,
                )
            ) {
                append(email)
            }
            pop()
        }
    }

    ClickableText(
        modifier = modifier,
        text = emailString,
        onClick = { offset ->
            emailString
                .getStringAnnotations(tag = EMAIL_ANNOTATION_TAG, start = offset, end = offset)
                .firstOrNull()?.let { emailRecipient ->
                    composeEmail(context, emailRecipient.item)
                }
        },
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun PersonsTelephoneNumber(
    modifier: Modifier = Modifier,
    phoneTypeName: String,
    telephone: String
) {
    val context = LocalContext.current
    val primaryColor = MaterialTheme.colorScheme.primary
    val telephoneNumber = remember(phoneTypeName, telephone) {
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                append("$phoneTypeName: ")
            }
            pushStringAnnotation(tag = PHONE_NUMBER_ANNOTATION_TAG, annotation = telephone)
            withStyle(style = SpanStyle(color = primaryColor)) {
                append(telephone)
            }
            pop()
        }
    }

    ClickableText(
        modifier = modifier,
        text = telephoneNumber,
        onClick = { offset ->
            telephoneNumber.getStringAnnotations(
                tag = PHONE_NUMBER_ANNOTATION_TAG,
                start = offset,
                end = offset
            ).firstOrNull()?.let { phoneNumber ->
                dialPhoneNumber(context, phoneNumber.item)
            }
        },
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun PersonsAddress(
    modifier: Modifier = Modifier,
    country: String,
    state: String,
    city: String,
    street: String,
    latitude: String,
    longitude: String,
) {
    val context = LocalContext.current
    val address = remember {
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                append(context.getString(R.string.details_screen_location))
            }
            append("\n\t")
            append(context.getString(R.string.details_screen_location_country) + ':')
            append("\n\t")
            append(context.getString(R.string.details_screen_location_state) + ':')
            append("\n\t")
            append(context.getString(R.string.details_screen_location_city) + ':')
            append("\n\t")
            append(context.getString(R.string.details_screen_location_street) + ':')
        }
    }
    val factualAddress = remember(country, state, city, street) {
        buildAnnotatedString {
            appendLine()
            append(country)
            appendLine()
            append(state)
            appendLine()
            append(city)
            appendLine()
            append(street)
        }
    }

    Row(
        modifier = modifier.clickable {
            showInMap(
                context = context,
                latitude = latitude,
                longitude = longitude,
            )
        }
    ) {
        Text(
            text = address,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.details_screen_location_spacer_width)))
        Text(
            text = factualAddress,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

private fun formatDate(date: String): String {
    if (date.isBlank()) return ""

    val stringBuilder = StringBuilder()
    val truncatedDate = date.takeWhile { it != 'T' }
    truncatedDate.split('-').reversed().forEach { stringBuilder.append(it).append('.') }
    stringBuilder.deleteAt(stringBuilder.length - 1)

    return stringBuilder.toString()
}

private fun composeEmail(context: Context, recipient: String) {
    if (recipient.isBlank()) return

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, recipient)
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

private fun dialPhoneNumber(context: Context, phoneNumber: String) {
    if (phoneNumber.isBlank()) return

    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

private fun showInMap(context: Context, latitude: String, longitude: String) {
    if (latitude.isBlank() || longitude.isBlank()) return

    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("geo:0,0?q=$latitude,$longitude(${context.getString(R.string.details_screen_location_message)})")
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}


private const val PHONE_NUMBER_ANNOTATION_TAG = "phone_number_annotation"
private const val EMAIL_ANNOTATION_TAG = "email_annotation"


@[Composable Preview]
fun UserInfoPreview() {
    Surface {
        UserInfo(userData = FakeUser)
    }
}