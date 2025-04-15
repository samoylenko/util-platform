package dev.samoylenko.util.platform

import dev.samoylenko.util.platform.PlatformUtils.logger
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readByteArray
import kotlinx.io.readString

object FileSystem {
    fun readText(path: Path) = SystemFileSystem.source(path).buffered().use { it.readString() }
    fun readTextOrNull(path: Path) = runCatching { readText(path) }
        .onFailure { logger.error(throwable = it) { "Could not read file '${this}'" } }
        .getOrNull()

    fun readBytes(path: Path) = SystemFileSystem.source(path).buffered().use { it.readByteArray() }
    fun readBytesOrNull(path: Path) = runCatching { readBytes(path) }
        .onFailure { logger.error(throwable = it) { "Could not read file '${this}'" } }
        .getOrNull()
}
