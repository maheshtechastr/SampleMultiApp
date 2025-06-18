plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.dagger.hilt)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.mpg.di"
}

dependencies {

    implementation(projects.models)
    implementation(projects.domain)
    implementation(projects.data)

    //hilt
    implementation(libs.android.hilt)
    ksp(libs.android.hilt.compiler)

    //Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.converter.gson)

    //Room
    implementation(libs.androidx.room.runtime)
    //this project uses Kotlin source, so use Kotlin Symbol Processing (KSP)
//    ksp(libs.androidx.room.compiler)
    //Kotlin Extensions and Coroutines support for Room
//    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}