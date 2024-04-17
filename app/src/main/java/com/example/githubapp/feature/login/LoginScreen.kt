package com.example.githubapp.feature.login

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubapp.feature.login.models.LoginScreenEvent
import com.example.githubapp.feature.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val redirectClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView,
            request: WebResourceRequest
        ): Boolean {
            viewModel.onEvent(LoginScreenEvent.OnRedirect(request.url.toString()))
            return true
        }
    }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = redirectClient
                settings.javaScriptEnabled = true
            }
        },
        update = { webView -> webView.loadUrl(state.url) },
        modifier = Modifier.fillMaxSize(),
    )
}
