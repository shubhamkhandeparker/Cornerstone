package com.shubham.cornerstone

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

object FightGearLinkOpener {

    fun open(
        context: Context,
        productUrl: String?
    ): Boolean {
        val safeUrl = productUrl
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?: return false

        val uri = runCatching {
            Uri.parse(safeUrl)
        }.getOrNull() ?: return false

        val scheme = uri.scheme?.lowercase()

        if (scheme != "https") {
            return false
        }

        val intent = Intent(
            Intent.ACTION_VIEW,
            uri
        ).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        return try {
            context.startActivity(intent)
            true
        } catch (_: ActivityNotFoundException) {
            false
        } catch (_: SecurityException) {
            false
        }
    }
}