pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven { url = uri("https://jitpack.io") }
    }
}
rootProject.name = "SampleMultiApp"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":theme")
include(":data")
include(":domain")
include(":presentation")
include(":models")
include(":di")
