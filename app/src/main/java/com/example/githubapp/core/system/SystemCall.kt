package com.example.githubapp.core.system

interface SystemCall {

    fun share(text: String)

    fun openInBrowser(url: String): Boolean
}
