package dev.samoylenko.util.platform

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

internal expect fun getCurrentPlatformImpl(): Platform
internal expect fun getCurrentOsImpl(): OperatingSystem
internal expect fun getEnvImpl(name: String): String?
internal expect fun getHomeDirImpl(): String?

object PlatformUtils {

    internal val logger = KotlinLogging.logger {}

    fun getCurrentPlatform(): Platform = getCurrentPlatformImpl()

    fun getCurrentOs(): OperatingSystem = getCurrentOsImpl()

    fun getEnv(name: String): String? = getEnvImpl(name)

    fun getHomeDir(): String? = getHomeDirImpl()

    // TODO: dangerous function, need some controls to ensure it doesn't delete anything outside the directory
    fun deleteRecursively(path: Path) {
        logger.warn { "Deleting $path" } // TODO: warnings for now just to watch what it deletes

        if (SystemFileSystem.metadataOrNull(path)?.isDirectory == true) {
            SystemFileSystem.list(path).forEach { deleteRecursively(it) }
        } else {
            SystemFileSystem.delete(path, mustExist = false)
        }
    }

    fun walkRecursively(path: Path): Collection<Path> =
        if (SystemFileSystem.metadataOrNull(path)?.isDirectory == true)
            SystemFileSystem.list(path).flatMap { walkRecursively(it) }
        else listOf(path)
}
