package com.example.a2020102527_main_project_2.ui.nav
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.a2020102527_main_project_2.ui.screen.home.HomeScreen
import com.example.a2020102527_main_project_2.ui.screen.runninghistory.RunningHistoryScreen

/*
    This code snippet defines the navigation setup for the home screen of the app.
    It utilizes a homeNavigation function, extending NavGraphBuilder, to create a nested navigation
     graph within the main navigation graph.
     This nested graph includes destinations for the recent run screen and the running history screen.
      Each destination is associated with its respective composable screen
      (HomeScreen and RunningHistoryScreen) and configured with appropriate parameters such as
       the NavController and PaddingValues.
 */
fun NavGraphBuilder.homeNavigation(
    navController: NavController,
    paddingValues: PaddingValues
) {
    navigation(
        startDestination = BottomNavDestination.Home.RecentRun.route,
        route = BottomNavDestination.Home.route
    ) {
        composable(
            route = BottomNavDestination.Home.RecentRun.route
        ) {
            HomeScreen(
                navController = navController,
                bottomPadding = paddingValues.calculateBottomPadding()
            )
        }

        composable(
            route = BottomNavDestination.Home.RunningHistory.route
        ) {
            RunningHistoryScreen(
                paddingValues = paddingValues,
                navController = navController
            )
        }

    }
}