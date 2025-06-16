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
}