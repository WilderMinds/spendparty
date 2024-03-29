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
import java.util.Date
import kotlin.math.abs

/**
 * @author Sam
 * Created 03/01/2024 at 9:09 pm
 */

@Composable
fun HomeScreen() {
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
                    targetDate = Date(),
                ),
                Account(
                    id = 1,
                    name = "New Zealand",
                    currency = "GHS",
                    balance = 4382,
                    targetAmount = 9871,
                    targetDate = Date(),
                ),
                Account(
                    id = 2,
                    name = "Droidfest Berlin",
                    currency = "GHS",
                    balance = 65892,
                    targetAmount = 70500,
                    targetDate = Date(),
                )
            )
        ),
        onAccountClick = {}
    )
}

@Composable
private fun HomeView(
    state: HomeScreenState,
    onAccountClick: () -> Unit
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
                AccountCard(
                    balance = account.displayBalance,
                    accountName = account.name,
                    target = account.displayTargetAmount,
                    percentageCompleteDisplay = account.percentageCompleteDisplay,
                    timeline = account.timeLeft,
                    percentageComplete = account.percentageComplete,
                    onAccountClick = onAccountClick
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
        Row(
            modifier = Modifier.padding(
                all = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f)
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
                        TimelineText(timeline = timeline)
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
                }
            }

            Box(
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
    }
}

@Composable
private fun TimelineText(timeline: Timeline) {
    val defaultStyle = MaterialTheme.typography.labelSmall.copy(
        color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = 0.7f
        )
    )
    val timelineTextStyle = when (timeline) {
        is Timeline.Infinity -> defaultStyle
        is Timeline.RelativeDate -> when {
            timeline.isOverdue -> defaultStyle.copy(
                color = MaterialTheme.colorScheme.error.copy(
                    alpha = 0.7f
                )
            )

            else -> defaultStyle
        }
    }

    return when (timeline) {
        Timeline.Infinity -> Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(id = R.drawable.infinity),
                contentDescription = null
            )
            HorizontalSpacer(size = 4.dp)
            Text(
                text = "unlimited",
                style = timelineTextStyle
            )
        }

        is Timeline.RelativeDate -> Text(
            text = timeline.toStringFormat(),
            style = timelineTextStyle
        )
    }
}

@Composable
private fun Timeline.RelativeDate.toStringFormat(): String {
    val num = abs(number)
    val overdueString = if (isOverdue) "overdue" else "left"
    return when (this) {
        is Timeline.RelativeDate.Days -> if (num == 0) "due today" else "$num days $overdueString"
        is Timeline.RelativeDate.Weeks -> "$num weeks $overdueString"
        is Timeline.RelativeDate.Months -> "$num months $overdueString"
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
                balance = "GHS 55,000",
                target = "GHS 350,000",
                accountName = "detty december",
                percentageCompleteDisplay = "34%",
                timeline = Timeline.RelativeDate.Days(76),
                percentageComplete = 0.7f,
                onAccountClick = {}
            )

            AccountCard(
                balance = "GHS 55,000",
                target = "GHS 350,000",
                accountName = "detty december",
                percentageCompleteDisplay = "34%",
                timeline = Timeline.Infinity,
                percentageComplete = 0.7f,
                onAccountClick = {}
            )

            AccountCard(
                balance = "GHS 55,000",
                target = "GHS 350,000",
                accountName = "detty december",
                percentageCompleteDisplay = "34%",
                timeline = Timeline.RelativeDate.Weeks(-9),
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
            onAccountClick = {},
            state = HomeScreenState(
                loading = false,
                accounts = listOf(
                    Account(
                        id = 0,
                        name = "Detty December",
                        currency = "GHS",
                        balance = 23000,
                        targetAmount = 100000,
                        targetDate = Date(),
                    ),
                    Account(
                        id = 0,
                        name = "New Zealand",
                        currency = "GHS",
                        balance = 4382,
                        targetAmount = 9871,
                        targetDate = Date(),
                    ),
                    Account(
                        id = 0,
                        name = "Droidfest Berlin",
                        currency = "GHS",
                        balance = 65892,
                        targetAmount = 70500,
                        targetDate = Date(),
                    )
                )
            )
        )
    }
}
