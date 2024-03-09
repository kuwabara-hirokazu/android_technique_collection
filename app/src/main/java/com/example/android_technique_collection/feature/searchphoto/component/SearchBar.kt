package com.example.android_technique_collection.feature.searchphoto.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchTextChanged: (String) -> Unit,
    onDone: () -> Unit,
    placeHolder: String = "",
) {
    val showClearButton by remember(searchQuery) {
        derivedStateOf { searchQuery.isNotEmpty() }
    }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TopAppBar(
        title = {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchTextChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .focusRequester(focusRequester),
                placeholder = {
                    Text(text = placeHolder)
                },
                trailingIcon = {
                    if (showClearButton) {
                        IconButton(
                            onClick = { onSearchTextChanged("") }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "close"
                            )
                        }
                    }
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        onDone()
                    }
                )
            )
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
