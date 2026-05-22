package com.vrushabhgaikar.vibeplayer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.ui.theme.CardBg
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurplePrimary

@Composable
fun AppSearchBar(
    value: String,
    onValueChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = LightGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(50)
            )
            .background(
                brush = Brush.horizontalGradient(
                    listOf(CardBg, PurplePrimary.copy(alpha = 0.2f))
                ),
                shape = RoundedCornerShape(50)
            )
            .clickable {
                focusRequester.requestFocus()
                keyboardController?.show()
            }
            .padding(horizontal = 25.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null, tint = LightGray
            )



            BasicTextField(
                value = value,
                onValueChange = {
                    val filteredText = it.filter { char ->
                        char.isLetterOrDigit() || char.isWhitespace()
                    }
                    onValueChange(filteredText)
                },
                singleLine = true,
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester)

            )

            if (value.isEmpty()) {
                Text(
                    text = "What do you want to listen to?",
                    color = LightGray
                )
            }

            if (value.isNotEmpty()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cross),
                    contentDescription = "Clear",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            onValueChange("")
                        }
                )
            }
        }
    }


}