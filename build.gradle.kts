import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootEnvSpec

group = "dev.samoylenko"
version = "0.1.1"

repositories { mavenCentral() }

plugins {
    signing

    alias(libs.plugins.kotlin.plugin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)

    alias(libs.plugins.maven.publish)
}

kotlin {
    explicitApi()

    withSourcesJar(publish = true)

    js {
        nodejs {
            // https://github.com/JetBrains/kotlin-wrappers/issues/2221
            useEsModules()
        }
    }

    jvm {}

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.io)
            api(libs.kotlin.logging)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        jsMain.dependencies {
            implementation(kotlinWrappers.node)
        }

        jvmTest.dependencies {
            api(libs.logback.classic)
        }
    }
}

signing {
    useGpgCmd()
}

mavenPublishing {
    signAllPublications()

    publishToMavenCentral()

    pom {
        name = "Platform Utils"
        description = "A basic platform utils library (Kotlin Multiplatform)"
        url = "https://github.com/samoylenko/util-platform"

        licenses {
            license {
                name = "The MIT License"
                url = "https://github.com/samoylenko/util-platform/blob/main/LICENSE"
            }
        }

        developers {
            developer {
                id = "samoylenko"
                name = "Michael Samoylenko"
                url = "https://github.com/samoylenko"
            }
        }

        scm {
            url = "https://github.com/samoylenko/util-platform"
            connection = "scm:git:git://github.com/samoylenko/util-platform.git"
            developerConnection = "scm:git:ssh://git@github.com:samoylenko/util-platform.git"
        }
    }
}

rootProject.plugins.withType(YarnPlugin::class.java) {
    rootProject.the<YarnRootEnvSpec>().apply {
        ignoreScripts.set(true)
        yarnLockAutoReplace.set(true)
        yarnLockMismatchReport.set(YarnLockMismatchReport.WARNING)
    }
}
