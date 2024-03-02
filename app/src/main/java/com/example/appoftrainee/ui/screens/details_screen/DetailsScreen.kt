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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.res.ResourcesCompat
import coil.compose.AsyncImage
import com.example.appoftrainee.R


@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    personId: String = ""
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.details_screen_photo_padding))
                .size(dimensionResource(R.dimen.details_screen_photo_size))
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally),
            model = "https://randomuser.me/api/portraits/men/75.jpg",
            contentDescription = stringResource(R.string.details_screen_photo_description)
        )
        Text(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.details_screen_name_padding_bottom))
                .align(Alignment.CenterHorizontally),
            text = "Shesterov Ilya Andreevich",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(
                R.string.details_screen_name,
                "male", 26, "1997-08-26"
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
            email = "sanektisru@gmail.com"
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.details_screen_divider_padding_vertical)))
        PersonsTelephoneNumber(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.details_screen_field_padding_horizontal)),
            phoneTypeName = stringResource(R.string.details_screen_phone_home),
            telephone = "+79994956184"
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.details_screen_telephone_spacer_height)))
        PersonsTelephoneNumber(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.details_screen_field_padding_horizontal)),
            phoneTypeName = stringResource(R.string.details_screen_phone_mobile),
            telephone = "+73822674196"
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.details_screen_divider_padding_vertical)))
        PersonsAddress(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.details_screen_field_padding_horizontal)),
            country = "Russia",
            state = "Siberian Federal District",
            city = "Tomsk",
            street = "Lomonosova 5A"
        )
    }
}

@Composable
fun PersonsEmail(modifier: Modifier = Modifier, email: String) {
    val emailString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
            append(stringResource(R.string.details_screen_email) + ": ")
        }
        pushStringAnnotation(tag = EMAIL_ANNOTATION_TAG, annotation = email)
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
            )
        ) {
            append(email)
        }
        pop()
    }

    ClickableText(
        modifier = modifier,
        text = emailString,
        onClick = { offset ->
            emailString
                .getStringAnnotations(tag = EMAIL_ANNOTATION_TAG, start = offset, end = offset)
                .firstOrNull()?.let { email ->

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
    val telephoneNumber = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
            append("$phoneTypeName: ")
        }
        pushStringAnnotation(tag = PHONE_NUMBER_ANNOTATION_TAG, annotation = telephone)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(telephone)
        }
        pop()
    }

    ClickableText(
        modifier = modifier,
        text = telephoneNumber,
        onClick = { offset ->

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
    street: String
) {
    val address = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight =  FontWeight.Medium)) {
            append(stringResource(R.string.details_screen_location))
        }
        append("\n\t")
        append(stringResource(R.string.details_screen_location_country) + ':')
        append("\n\t")
        append(stringResource(R.string.details_screen_location_state) + ':')
        append("\n\t")
        append(stringResource(R.string.details_screen_location_city) + ':')
        append("\n\t")
        append(stringResource(R.string.details_screen_location_street) + ':')
    }
    val factualAddress = buildAnnotatedString {
        appendLine()
        append(country)
        appendLine()
        append(state)
        appendLine()
        append(city)
        appendLine()
        append(street)
    }

    Row(
        modifier = modifier.clickable {

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


private const val PHONE_NUMBER_ANNOTATION_TAG = "phone_number_annotation"
private const val EMAIL_ANNOTATION_TAG = "email_annotation"


@[Composable Preview]
fun DetailsScreenPreview() {
    Surface {
        DetailsScreen()
    }
}