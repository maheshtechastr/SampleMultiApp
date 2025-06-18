plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.dagger.hilt)
    alias(libs.plugins.google.devtools.ksp)
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
    implementation(libs.androidx.paging.runtime.ktx)

    //Room
    implementation(libs.androidx.room.runtime)
    //this project uses Kotlin source, so use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)
    //Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockito.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.paging.testing) // to test paging

    testImplementation(kotlin("test"))
}