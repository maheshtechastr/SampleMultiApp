plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.dagger.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.mpg.domain"
//    buildFeatures {
//        compose = true
//    }
}

dependencies {
    implementation(projects.models)

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
    implementation(libs.androidx.paging.common.jvm)

    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)

//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
}