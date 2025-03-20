package org.example.sleepapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform