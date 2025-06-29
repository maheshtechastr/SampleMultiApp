import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.dagger.hilt) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin.serialization) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
}

/**
 *  make common config for all the modules in this project */
fun BaseExtension.defaultConfig() {
    compileSdkVersion(35)

    defaultConfig {
        minSdk = 24
        targetSdk = 35
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    composeOptions{
        kotlinCompilerExtensionVersion = libs.versions.kotlin.get()
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packagingOptions {
        resources {
            exclude("META-INF/gradle/incremental.annotation.processors")
        }
    }

    testOptions {
//        unitTests.isIncludeAndroidResources = true
    }

}

fun PluginContainer.applyDefaultPlugin(project: Project) {
    whenPluginAdded {
        //what type of plugins added
        when (this) {
            is AppPlugin -> {
                project.extensions
                    .getByType<AppExtension>()
                    .apply {
                        defaultConfig()
                    }
            }

            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        defaultConfig()
                    }
            }

            is JavaPlugin -> {
                project.extensions.getByType<JavaPluginExtension>()
                    .apply {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }
            }
        }
    }
}
subprojects {
    project.plugins.applyDefaultPlugin(project)
    //added to suppress material3 warning
    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.addAll(
                listOf(
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
                )
            )
        }
    }
}