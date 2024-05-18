package com.example.a2020102527_main_project_2.ui.nav
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a2020102527_main_project_2.ui.screen.profile.ProfileScreen
import com.example.a2020102527_main_project_2.ui.nav.Destination.CurrentRun
import com.example.a2020102527_main_project_2.ui.screen.currentrun.CurrentRunScreen
import com.example.a2020102527_main_project_2.ui.screen.onboard.OnBoardScreen
import com.example.a2020102527_main_project_2.ui.screen.runstats.RunStatsScreen

/*
    These two code snippets define the navigation setup for the app using Jetpack Compose and the
    Navigation component.
    They establish the navigation graph, specifying various destinations and their corresponding composables.
     The destinations include the home screen, profile screen, current run screen, onboarding screen,
     and run statistics screen. Each destination is associated with a composable function that defines its UI.
     The Navigation composable serves as an entry point for the navigation setup, while SetupNavGraph
     configures the navigation graph using the NavHost composable.
 */
@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues()
) {
    SetupNavGraph(
        navController = navController,
        paddingValues = paddingValues
    )
}

@Composable
private fun SetupNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDestination.Home.route
    ) {
        homeNavigation(navController, paddingValues)

        composable(
            route = BottomNavDestination.Profile.route
        ) {
            ProfileScreen(paddingValues.calculateBottomPadding())
        }

        composable(
            route = CurrentRun.route,
            deepLinks = CurrentRun.deepLinks
        ) {
            CurrentRunScreen(navController)
        }

        composable(
            route = Destination.OnBoardingDestination.route
        ) {
            OnBoardScreen(navController = navController)
        }

        composable(route = Destination.RunStats.route) {
            RunStatsScreen(
                navigateUp = { navController.navigateUp() }
            )
        }
    }

}