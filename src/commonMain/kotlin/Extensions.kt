package dev.samoylenko.util.platform

import kotlinx.io.files.Path

object Extensions {
    fun Path.readText() = FileSystem.readText(this)
    fun Path.readTextOrNull() = FileSystem.readTextOrNull(this)

    fun Path.readBytes() = FileSystem.readBytes(this)
    fun Path.readBytesOrNull() = FileSystem.readBytesOrNull(this)
}
