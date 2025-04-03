import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") version "2.0.21"
    kotlin("plugin.compose") version "2.0.21"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20-RC"
    id("org.jetbrains.compose") version "1.7.0"
}


repositories {
    mavenCentral()
    google()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://repo1.maven.org/maven2/") }
    maven { url = uri("https://github.com/bytebeats/compose-charts") }
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm("desktop")
    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.material3.desktop)

            // Добавление зависимостей для lifecycle
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(files("libs/compose-charts-desktop-0.1.0.jar"))
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

            implementation(files("libs/compose-charts-desktop-0.1.0.jar"))

            //compose
            implementation("io.github.bytebeats:compose-charts:0.1.2")
            implementation("androidx.compose.ui:ui:1.4.0")
            implementation("androidx.compose.material3:material3:1.0.0")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.3")
            implementation ("androidx.compose.material:material-icons-extended:1.4.0")

            //RETROFIT
            implementation("com.squareup.retrofit2:retrofit:2.11.0")
            implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
            implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

            // Для использования viewModel с Compose
           // implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.3.0")

            // Также добавьте зависимости для ViewModel
           // implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")

            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

            //MockWebServer
            implementation("com.squareup.okhttp3:mockwebserver:4.12.0")
        }
    }
}

compose.desktop {
    application {
        mainClass = "org.example.project.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.project"
            packageVersion = "1.0.0"
        }
    }
}

