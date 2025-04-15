package dev.samoylenko.util.platform

actual fun getCurrentPlatformImpl(): Platform = Platform.JVM

actual fun getCurrentOsImpl(): OperatingSystem =
    if (System.getProperty("os.name").startsWith("Win", ignoreCase = true)) OperatingSystem.WINDOWS
    else OperatingSystem.POSIX

actual fun getEnvImpl(name: String): String? =
    System.getenv(name)

internal actual fun getHomeDirImpl(): String? =
    System.getProperty("user.home")
