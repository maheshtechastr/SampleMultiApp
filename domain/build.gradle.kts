plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.dagger.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.mpg.domain"
}

dependencies {
    implementation(projects.models)

    implementation(libs.androidx.paging.compose)
    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockito.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.paging.testing) // to test paging
    testImplementation(kotlin("test"))
}