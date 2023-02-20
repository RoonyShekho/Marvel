package com.gateway.marvel.ui.screen.components.searchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.unit.dp


@ExperimentalComposeUiApi
@Composable
fun SearchTextField(
    onQueryChange: (String) -> Unit,
    leadingIcon: ImageVector = Icons.Default.Search,
    onSearchClicked: () -> Unit,
    query: String,
    focusState: (Boolean) -> Unit,
    trailingIcon: ImageVector? = null,
    onRemoveClicked: () -> Unit
) {


    var copyText by remember {
        mutableStateOf("")
    }


    val focusRequester = FocusRequester()

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(20.dp)
    ) {
        TextField(value = query, onValueChange = {
            onQueryChange(it)
            copyText = it
        },
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        focusState(true)
                    } else {
                        focusState(false)
                    }
                },
            colors = TextFieldDefaults.textFieldColors(cursorColor = Color.Black, textColor = Color.Black),
            label = { Text(text = "Enter a word..") },
            trailingIcon = {
                if (trailingIcon != null) {
                    IconButton(onClick = onRemoveClicked) {
                        Icon(
                            trailingIcon,
                            contentDescription = null,
                            tint = Red
                        )
                    }
                }
            },
            leadingIcon = {
                IconButton(onClick = onSearchClicked) {
                    Icon(
                        leadingIcon,
                        contentDescription = null,
                        tint = Red
                    )
                }
            }
        )
    }
}

