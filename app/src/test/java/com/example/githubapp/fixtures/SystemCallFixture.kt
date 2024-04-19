package com.example.githubapp.fixtures

import com.example.githubapp.core.system.SystemCall

class SystemCallFixture : SystemCall {

    var wasSharedCalled = false
        private set

    var wasOpenInBrowserCalled = false
        private set

    override fun share(text: String) {
        wasSharedCalled = true
    }

    override fun openInBrowser(url: String): Boolean {
        wasOpenInBrowserCalled = true
        return true
    }
}
