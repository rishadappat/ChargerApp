package com.appat.chargerapp.ui.customviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.appat.chargerapp.ui.screens.dashboard.viewmodel.DashboardScreenViewModel
import com.appat.chargerapp.ui.theme.secondaryText
import com.appat.chargerapp.utility.clearFocusOnKeyboardDismiss

@Composable
fun SearchField(placeholder: String,
                viewModel: DashboardScreenViewModel
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Row(modifier = Modifier
        .background(shape = RoundedCornerShape(10.dp),
            color = Color.White),
        verticalAlignment = Alignment.CenterVertically) {
        val searchViewTextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = MaterialTheme.colors.primary)
        val focusRequester = remember { FocusRequester() }

        BasicTextField(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .clearFocusOnKeyboardDismiss(),
            value = uiState.value.searchText,
            onValueChange = { value ->
                viewModel.performSearch(value)
            },
            textStyle = searchViewTextStyle,
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            decorationBox = { innerTextField ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (uiState.value.searchText.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = placeholder,
                            color = secondaryText,
                            style = searchViewTextStyle
                        )
                    }
                }
                innerTextField()
            }
        )
    }
}