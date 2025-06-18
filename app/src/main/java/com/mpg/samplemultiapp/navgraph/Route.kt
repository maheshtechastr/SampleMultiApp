package com.mpg.samplemultiapp.navgraph

sealed class Route(
    val route: String,
//    val arguments: List<NamedNavArgument> = emptyList()
) {

    object HomeScreen : Route(route = "homeScreen")
    object BookmarkScreen : Route(route = "bookmarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

}

