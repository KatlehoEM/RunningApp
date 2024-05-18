package com.example.a2020102527_main_project_2.ui.utils
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.a2020102527_main_project_2.ui.nav.BottomNavDestination

/*
    The navigateToBottomNavDestination extension function for NavController simplifies
    navigation to a bottom navigation destination. It navigates to the specified destination,
     ensuring single top behavior, saving state, and restoring state while popping up to the start
     destination of the graph. This function streamlines navigation management within bottom navigation
      architectures in Android applications.
 */
fun NavController.navigateToBottomNavDestination(item: BottomNavDestination) {
    navigate(item.route) {
        popUpTo(graph.findStartDestination().id) {
            this.saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}