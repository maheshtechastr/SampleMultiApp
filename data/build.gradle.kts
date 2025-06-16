plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.dagger.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.mpg.data"
}

dependencies {
    implementation(projects.models)
    implementation(projects.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.compose)

    implementation(libs.android.hilt)
    implementation(libs.android.hilt.compiler)

    //Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.converter.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}