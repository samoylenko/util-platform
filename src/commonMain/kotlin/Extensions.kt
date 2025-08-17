package dev.samoylenko.util.platform

import kotlinx.io.files.Path

public object Extensions {
    public fun Path.readText(): String = FileSystem.readText(this)
    public fun Path.readTextOrNull(): String? = FileSystem.readTextOrNull(this)

    public fun Path.readBytes(): ByteArray = FileSystem.readBytes(this)
    public fun Path.readBytesOrNull(): ByteArray? = FileSystem.readBytesOrNull(this)
}
