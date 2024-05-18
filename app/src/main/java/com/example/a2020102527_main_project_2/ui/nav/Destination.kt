package com.example.a2020102527_main_project_2.ui.nav
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.navigation.NavController
import androidx.navigation.navDeepLink

/*
    This code defines a sealed class Destination representing different destinations within
    the app's navigation.
    It includes objects for onboarding, current run, and run stats screens.
    Additionally, it provides functionality for navigating to specific destinations,
    handling deep links for the current run screen, and navigating back to the home screen after onboarding.
 */
sealed class Destination(val route: String) {

    object OnBoardingDestination : Destination("on_boarding") {
        fun navigateToHome(navController: NavController) {
            navController.navigate(BottomNavDestination.Home.route) {
                popUpTo(route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    object CurrentRun : Destination("current_run") {
        val currentRunUriPattern = "https://runtrack.katleho.com/$route"
        val deepLinks = listOf(
            navDeepLink {
                uriPattern = currentRunUriPattern
            }
        )
    }

    data object RunStats : Destination("run_stats")

    //global navigation
    companion object {
        fun navigateToCurrentRunScreen(navController: NavController) {
            navController.navigate(CurrentRun.route)
        }
    }

}
