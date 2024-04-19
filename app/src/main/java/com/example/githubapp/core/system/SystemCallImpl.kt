package com.example.githubapp.core.system

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SystemCallImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SystemCall {

    companion object {
        private const val TEXT_PLAIN_TYPE = "text/plain"
    }

    override fun share(text: String) {
        context.startActivity(
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                type = TEXT_PLAIN_TYPE
            }
        )
    }

    override fun openInBrowser(url: String): Boolean = try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
        true
    } catch (e: ActivityNotFoundException) {
        false
    }
}
