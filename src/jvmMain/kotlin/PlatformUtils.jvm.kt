package dev.samoylenko.util.platform

internal actual fun getCurrentPlatformImpl(): Platform = Platform.JVM

internal actual fun getCurrentOsImpl(): OperatingSystem =
    if (System.getProperty("os.name").startsWith("Win", ignoreCase = true)) OperatingSystem.WINDOWS
    else OperatingSystem.POSIX

internal actual fun getEnvImpl(name: String): String? =
    System.getenv(name)

internal actual fun getHomeDirImpl(): String? =
    System.getProperty("user.home")
