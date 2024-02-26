package com.samdev.spendparty.ui.home.state

import com.samdev.spendparty.domain.models.Account
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

/**
 * @author Sam
 * Created 06/01/2024 at 11:26 pm
 */
data class HomeScreenState(
    val loading: Boolean = false,
    val accounts: List<Account> = emptyList(),
)