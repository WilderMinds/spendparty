package com.samdev.spendparty.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.samdev.core.ui.theme.SpendPartyTheme

/**
 * @author Sam
 * Created 03/01/2024 at 9:09 pm
 */

@Composable
fun HomeScreen() {

}

@Composable
private fun HomeView() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Accounts")
            Icon(
                painter = rememberVectorPainter(image = Icons.Outlined.Notifications),
                contentDescription = null
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeViewPreview() {
    SpendPartyTheme {
        HomeView()
    }
}
