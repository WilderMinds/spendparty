package com.samdev.spendparty.domain.models

import java.sql.Date

data class Topup(
    val id: Int,
    val amount: Long,
    val currency: String,
    val date: Date
)
