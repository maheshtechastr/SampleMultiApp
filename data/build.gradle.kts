plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.dagger.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.mpg.data"
//    buildFeatures {
//        compose = true
//    }
}

dependencies {
    implementation(projects.models)
    implementation(projects.domain)

    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)

    implementation(libs.androidx.paging.common.jvm)

    implementation(libs.android.hilt)
    implementation(libs.android.hilt.compiler)

    //Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.converter.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}