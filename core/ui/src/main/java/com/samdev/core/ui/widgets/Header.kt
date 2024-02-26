package com.samdev.core.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samdev.core.ui.theme.SpendPartyTheme

/**
 * @author Sam
 * Created 06/01/2024 at 10:42 pm
 */
@Composable
fun Header(
    title: String,
    modifier: Modifier = Modifier,
    leading: @Composable () -> Unit = {},
    trailing: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leading()
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        }
        trailing()
    }
}

@Composable
fun NavigationHeader(
    title: String,
    modifier: Modifier = Modifier,
    onBackPressed: (() -> Unit)? = null,
    trailing: @Composable () -> Unit = {}
) {
    Header(
        modifier = modifier,
        title = title,
        leading = {
            if (onBackPressed != null) {
                Icon(
                    modifier = Modifier
                        .clickable(
                            role = Role.Button,
                            onClick = onBackPressed,
                        )
                        .padding(
                            end = 16.dp
                        ),
                    painter = rememberVectorPainter(
                        image = Icons.Outlined.ArrowBack,
                    ),
                    contentDescription = null
                )
            }
        },
        trailing = trailing
    )
}


@Preview(showBackground = true)
@Composable
private fun PreviewNavigationHeader() {
    SpendPartyTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NavigationHeader(
                title = "Account"
            )

            NavigationHeader(
                title = "Account Details",
                onBackPressed = {}
            )

            NavigationHeader(
                title = "New Account",
                onBackPressed = {},
                trailing = {
                    Icon(
                        painter = rememberVectorPainter(
                            image = Icons.Outlined.Notifications
                        ),
                        contentDescription = null
                    )
                }
            )

            NavigationHeader(
                title = "Other Account",
                trailing = {
                    Icon(
                        painter = rememberVectorPainter(
                            image = Icons.Outlined.Notifications
                        ),
                        contentDescription = null
                    )
                }
            )
        }
    }
}
