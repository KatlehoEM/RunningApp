package com.example.a2020102527_main_project_2.ui
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.a2020102527_main_project_2.core.tracking.location.LocationUtils
import com.example.a2020102527_main_project_2.ui.screen.main.MainScreen
import com.example.a2020102527_main_project_2.ui.theme._2020102527_Main_Project_2Theme
import com.example.a2020102527_main_project_2.ui.utils.component.LocationPermissionRequestDialog
import com.example.a2020102527_main_project_2.utils.RunUtils
import com.example.a2020102527_main_project_2.utils.RunUtils.hasAllPermission
import com.example.a2020102527_main_project_2.utils.RunUtils.hasLocationPermission
import com.example.a2020102527_main_project_2.utils.RunUtils.openAppSetting
import dagger.hilt.android.AndroidEntryPoint

/*
    The MainActivity is annotated with @AndroidEntryPoint for Hilt integration and
    handles UI setup and permission requests. It displays the main screen composed of
    navigation components and manages permission requests for location access.
    The PermissionRequester composable checks and requests necessary permissions,
    showing rationale dialogs if needed. Additionally, it handles activity results,
    displaying a toast if location services are not enabled.
    The MainScreenPreview function provides a preview of the main screen layout.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2020102527_Main_Project_2Theme {
                PermissionRequester()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(rememberNavController())
                }
            }
        }
    }

    @Composable
    private fun PermissionRequester() {
        var showPermissionDeclinedRationale by rememberSaveable { mutableStateOf(false) }
        var showRationale by rememberSaveable { mutableStateOf(false) }
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                it.forEach { (permission, isGranted) ->
                    if (!isGranted && RunUtils.locationPermissions.contains(permission)) {
                        showPermissionDeclinedRationale = true
                    }
                }
            }
        )
        if (showPermissionDeclinedRationale)
            LocationPermissionRequestDialog(
                onDismissClick = {
                    if (!hasLocationPermission())
                        finish()
                    else showPermissionDeclinedRationale = false
                },
                onOkClick = { openAppSetting() }
            )
        if (showRationale)
            LocationPermissionRequestDialog(
                onDismissClick = ::finish,
                onOkClick = {
                    showRationale = false
                    permissionLauncher.launch(RunUtils.allPermissions)
                }
            )
        LaunchedEffect(key1 = Unit) {
            when {
                hasAllPermission() -> return@LaunchedEffect
                RunUtils.locationPermissions.any { shouldShowRequestPermissionRationale(it) } -> showRationale =
                    true

                else -> permissionLauncher.launch(RunUtils.allPermissions)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LocationUtils.LOCATION_ENABLE_REQUEST_CODE && resultCode != Activity.RESULT_OK) {
            Toast.makeText(
                this,
                "Please enable GPS to get proper running statistics.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    _2020102527_Main_Project_2Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(rememberNavController())
        }
    }
}