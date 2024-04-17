package com.example.githubapp.fixtures

import com.example.githubapp.core.system.SystemCall

class SystemCallFixture : SystemCall {

    var wasSharedCalled = false
        private set

    override fun share(text: String) {
        wasSharedCalled = true
    }
}
