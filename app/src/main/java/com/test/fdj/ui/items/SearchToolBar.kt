package com.test.fdj.ui.items

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.test.fdj.R
import com.test.fdj.ui.theme.testFDJTheme

@SuppressLint("ComposableNaming")
@Composable
fun searchToolBar(
    categories: State<List<String>>,
    text: MutableState<String>,
    onCategoriesUpdateNeeded: (String) -> Unit = {},
    onSelectCategory: (String) -> Unit = {},
    autoCompleteDefaultValue: Boolean = false,
) {
    var isAutoCompleteVisible by remember { mutableStateOf(autoCompleteDefaultValue) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Column {
        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
                onCategoriesUpdateNeeded(it)
                isAutoCompleteVisible = true
            },
            label = { Text(stringResource(id = (R.string.searchtool_label))) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            trailingIcon =
                {
                    IconButton(onClick = {
                        text.value = ""
                        focusRequester.requestFocus()
                    }) {
                        Icon(Icons.Filled.Clear, contentDescription = null)
                    }
                },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        if (it.hasFocus) {
                            onCategoriesUpdateNeeded(text.value)
                            isAutoCompleteVisible = true
                        }
                    }.focusRequester(focusRequester),
            singleLine = true,
        )
        if (isAutoCompleteVisible) {
            LazyColumn {
                items(categories.value) { category ->
                    ClickableText(
                        text = AnnotatedString(category),
                        onClick = {
                            text.value = category
                            onSelectCategory(category)
                            isAutoCompleteVisible = false
                            focusManager.clearFocus()
                        },
                        modifier =
                            Modifier
                                .padding(
                                    horizontal = Dp(10f),
                                    vertical = Dp(12f),
                                ).fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Preview(showBackground = true)
@Composable
fun searchToolBarPreview() {
    val exampleList =
        remember {
            mutableStateOf(
                listOf(
                    "Solution 1",
                    "Solution 2",
                    "Solution 3",
                ),
            )
        }
    val text = remember { mutableStateOf("") }

    testFDJTheme {
        searchToolBar(
            categories = exampleList,
            text = text,
            autoCompleteDefaultValue = true,
        )
    }
}
