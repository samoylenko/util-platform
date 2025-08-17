package dev.samoylenko.util.platform

import dev.samoylenko.util.platform.PlatformUtils.logger
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readByteArray
import kotlinx.io.readString

public object FileSystem {
    public fun readText(path: Path): String = SystemFileSystem.source(path).buffered().use { it.readString() }
    public fun readTextOrNull(path: Path): String? = runCatching { readText(path) }
        .onFailure { logger.error(throwable = it) { "Could not read file '${this}'" } }
        .getOrNull()

    public fun readBytes(path: Path): ByteArray = SystemFileSystem.source(path).buffered().use { it.readByteArray() }
    public fun readBytesOrNull(path: Path): ByteArray? = runCatching { readBytes(path) }
        .onFailure { logger.error(throwable = it) { "Could not read file '${this}'" } }
        .getOrNull()
}
