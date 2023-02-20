package com.gateway.marvel.ui.screen.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    trailingIcon: ImageVector? = null,
    leadingIcon: ImageVector? = null,
    readOnly: Boolean,
    onValueChange: (String) -> Unit,
    value: String,
    enabled: Boolean,
    onTrailingButtonClick: () -> Unit,
    onSearchBarClicked: () -> Unit,
) {


    Row(horizontalArrangement = Arrangement.Center, modifier = modifier
        .clickable {
            onSearchBarClicked()
        }
        .wrapContentSize()
    ) {
        OutlinedTextField(
            value = value,
            label = { Text(text = "Search for Marvel characters") },
            onValueChange = onValueChange,
            // to make the function reusable
            // (if you want from the search bar
            // to have a trailing or leading icon or not have them)

            leadingIcon = {
                if (leadingIcon != null) {
                    Icon(imageVector = leadingIcon, contentDescription = null)
                }
            },
            trailingIcon = {
                if (trailingIcon != null)
                    IconButton(onClick = onTrailingButtonClick) {
                        Icon(imageVector = trailingIcon, contentDescription = null)
                    }
            },
            readOnly = readOnly,
            enabled = enabled
        )
    }
}