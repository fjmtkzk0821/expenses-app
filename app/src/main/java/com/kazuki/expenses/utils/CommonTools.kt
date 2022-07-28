package com.kazuki.expenses.utils

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CommonTools {
    companion object {
//        const val DEFAULT_DATEFORMAT = "yyyy-MM-dd";
        fun isNumeric(value: String): Boolean {
            return value.toDoubleOrNull() != null
        }

        fun toCurrency(value: String): String {
            return NumberFormat.getCurrencyInstance().format(value.toDouble())
        }
    }
}