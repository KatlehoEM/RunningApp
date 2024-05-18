package com.example.a2020102527_main_project_2.ui.nav
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.a2020102527_main_project_2.R

/*
    This code defines a sealed class BottomNavDestination representing bottom navigation destinations
     within the app.
     It includes objects for the home screen, profile screen, recent run, and running history screens.
      Each destination has associated icons and methods for navigating to related screens using a
       NavController.
 */
sealed class BottomNavDestination(
    route: String,
    @DrawableRes
    val icon: Int
) : Destination(route) {

    @Composable
    fun getIconVector() = ImageVector.vectorResource(icon)

    object Home : BottomNavDestination(route = "home", icon = R.drawable.ic_menu) {

        fun navigateToOnBoardingScreen(navController: NavController) {
            navController.navigate(OnBoardingDestination.route)
        }

        fun navigateToRunStats(navController: NavController) {
            navController.navigate(RunStats.route)
        }

        object RecentRun : Destination("recent_run") {
            fun navigateToRunningHistoryScreen(navController: NavController) {
                navController.navigate(RunningHistory.route)
            }
        }

        object RunningHistory : Destination("running_history")

    }

    object Profile : BottomNavDestination(route = "profile", icon = R.drawable.ic_profile)

}