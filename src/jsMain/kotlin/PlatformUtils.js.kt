package dev.samoylenko.util.platform

actual fun getCurrentPlatformImpl(): Platform = Platform.JS

actual fun getCurrentOsImpl(): OperatingSystem =
    if (jsTypeOf(node.process.process) === "object") {
        if (node.process.process.platform === node.process.Platform.win32) OperatingSystem.WINDOWS
        else OperatingSystem.POSIX
    } else OperatingSystem.BROWSER

actual fun getEnvImpl(name: String): String? =
    if (getCurrentOsImpl() != OperatingSystem.BROWSER) node.process.process.env[name]
    else null

internal actual fun getHomeDirImpl(): String? =
    if (getCurrentOsImpl() != OperatingSystem.BROWSER) node.os.homedir()
    else null
