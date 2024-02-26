package com.samdev.spendparty.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samdev.core.ui.extensions.HorizontalSpacer
import com.samdev.core.ui.extensions.VerticalSpacer
import com.samdev.core.ui.theme.SpendPartyTheme
import com.samdev.core.ui.widgets.NavigationHeader
import com.samdev.spendparty.R
import com.samdev.spendparty.domain.models.Account
import com.samdev.spendparty.domain.models.Timeline
import com.samdev.spendparty.ui.home.state.HomeScreenState
import java.time.LocalDate

/**
 * @author Sam
 * Created 03/01/2024 at 9:09 pm
 */

@Composable
fun HomeScreen() {

}

@Composable
private fun HomeView(
    state: HomeScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(all = 16.dp)
    ) {

        NavigationHeader(
            title = stringResource(R.string.accounts),
            trailing = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Notifications),
                    contentDescription = null
                )
            }
        )

        VerticalSpacer(size = 16.dp)

        LazyColumn(
            state = rememberLazyListState()
        ) {
            itemsIndexed(state.accounts) { index, account ->
                AccountCardAlt(
                    balance = account.displayBalance,
                    accountName = account.name,
                    target = account.displayTargetAmount,
                    percentageCompleteDisplay = account.percentageCompleteDisplay,
                    timeline = account.timeLeft,
                    percentageComplete = account.percentageComplete,
                    onAccountClick = {}
                )

                if (index < state.accounts.lastIndex) {
                    VerticalSpacer(size = 12.dp)
                }

            }
        }
    }
}

@Composable
fun AccountCard(
    balance: String = "",
    accountName: String,
    target: String,
    timeline: Timeline,
    percentageComplete: Float,
    percentageCompleteDisplay: String,
    balanceOfTarget: String = "",
    onAccountClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                role = Role.Button,
                onClick = onAccountClick
            ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    all = 16.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CashIcon()
                HorizontalSpacer(size = 8.dp)
                Text(
                    text = accountName,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            VerticalSpacer(size = 8.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = balanceOfTarget,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            VerticalSpacer(size = 8.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .weight(1f)
                        .clip(
                            shape = RoundedCornerShape(
                                size = 20.dp
                            )
                        ),
                    progress = { percentageComplete },
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.2f
                    )
                )
                HorizontalSpacer(size = 8.dp)
                Text(
                    text = percentageCompleteDisplay,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            VerticalSpacer(size = 4.dp)
            Text(
                text = timeline.toStringFormat(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun AccountCardAlt(
    balance: String,
    target: String,
    accountName: String,
    timeline: Timeline,
    percentageComplete: Float,
    percentageCompleteDisplay: String,
    onAccountClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                role = Role.Button,
                onClick = onAccountClick
            ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    all = 16.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CashIcon()
                HorizontalSpacer(size = 12.dp)
                Column {
                    Text(
                        text = accountName,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = timeline.toStringFormat(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.7f
                            )
                        )
                    )
                }
            }
            VerticalSpacer(size = 8.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = balance,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(
                        text = "/ $target",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.5f
                            )
                        )
                    )
                }
                Box (
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(65.dp),
                        progress = { percentageComplete },
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.2f
                        )
                    )
                    HorizontalSpacer(size = 8.dp)
                    Text(
                        text = percentageCompleteDisplay,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            /*VerticalSpacer(size = 4.dp)
            Text(
                text = timeline.toStringFormat(),
                style = MaterialTheme.typography.labelMedium
            )*/
        }
    }
}

@Composable
private fun Timeline.toStringFormat(): String {
    return when (this) {
        is Timeline.Infinity -> "unlimited"
        is Timeline.TimeLeft.Days -> "$number days left"
        is Timeline.TimeLeft.Weeks -> "$number weeks left"
    }
}

@Composable
private fun CashIcon() {
    Box(
        modifier = Modifier
            .size(34.dp)
            .clip(shape = CircleShape)
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(id = R.drawable.money),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = Color.White
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountBalanceCardPreview() {
    SpendPartyTheme {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AccountCard(
                balanceOfTarget = "GHS 55,000 / GHS 350,000",
                accountName = "detty december",
                target = "GHS 1,000,000",
                percentageCompleteDisplay = "34%",
                timeline = Timeline.TimeLeft.Weeks(12),
                percentageComplete = 0.5f,
                onAccountClick = {}
            )

            AccountCardAlt(
                balance = "GHS 55,000",
                target = "GHS 350,000",
                accountName = "detty december",
                percentageCompleteDisplay = "34%",
                timeline = Timeline.TimeLeft.Days(76),
                percentageComplete = 0.7f,
                onAccountClick = {}
            )

            AccountCardAlt(
                balance = "GHS 55,000",
                target = "GHS 350,000",
                accountName = "detty december",
                percentageCompleteDisplay = "34%",
                timeline = Timeline.Infinity,
                percentageComplete = 0.7f,
                onAccountClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeViewPreview() {
    SpendPartyTheme {
        HomeView(
            state = HomeScreenState(
                loading = false,
                accounts = listOf(
                    Account(
                        id = 0,
                        name = "Detty December",
                        currency = "GHS",
                        balance = 23000,
                        targetAmount = 100000,
                        targetDate = LocalDate.now(),
                    ),
                    Account(
                        id = 0,
                        name = "New Zealand",
                        currency = "GHS",
                        balance = 4382,
                        targetAmount = 9871,
                        targetDate = LocalDate.now(),
                    ),
                    Account(
                        id = 0,
                        name = "Droidfest Berlin",
                        currency = "GHS",
                        balance = 65892,
                        targetAmount = 70500,
                        targetDate = LocalDate.now(),
                    )
                )
            )
        )
    }
}
