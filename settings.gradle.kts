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
    }
}
rootProject.name = "SampleMultiApp"

//for read all module in other modules by projects.<module-name> dynamically
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":theme")
include(":data")
include(":domain")
include(":features")
include(":models")
include(":di")
