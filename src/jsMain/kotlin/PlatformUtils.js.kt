package dev.samoylenko.util.platform

internal actual fun getCurrentPlatformImpl(): Platform = Platform.JS

internal actual fun getCurrentOsImpl(): OperatingSystem =
    if (jsTypeOf(node.process.process) === "object") {
        if (node.process.process.platform === node.process.Platform.win32) OperatingSystem.WINDOWS
        else OperatingSystem.POSIX
    } else OperatingSystem.BROWSER

internal actual fun getEnvImpl(name: String): String? =
    if (getCurrentOsImpl() != OperatingSystem.BROWSER) node.process.process.env[name]
    else null

internal actual fun getHomeDirImpl(): String? =
    if (getCurrentOsImpl() != OperatingSystem.BROWSER) node.os.homedir()
    else null
