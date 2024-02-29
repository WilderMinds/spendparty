package com.samdev.spendparty.extensions

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.samdev.core.ui.theme.SpendPartyTheme

fun ComponentActivity.setAppContent(
    content: @Composable () -> Unit
) {
    setContent {
        SpendPartyTheme {
            content()
        }
    }
}
