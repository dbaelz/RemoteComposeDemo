plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ktor)
    application
}

group = "de.dbaelz.rcdemo"
version = "1.0.0"
application {
    mainClass.set("de.dbaelz.rcdemo.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.di)
    implementation(libs.ktor.server.netty)

    implementation(libs.androidx.compose.remote.core)
    implementation(libs.androidx.compose.remote.creation.core)
    implementation(libs.androidx.compose.remote.creation)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material3)

    testImplementation(libs.ktor.server.testhost)
    testImplementation(libs.kotlin.testJunit)
}