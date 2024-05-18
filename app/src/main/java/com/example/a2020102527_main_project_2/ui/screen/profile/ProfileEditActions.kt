package com.example.a2020102527_main_project_2.ui.screen.profile
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.net.Uri

/*
    The ProfileEditActions interface defines methods for editing user profile information,
    including starting and cancelling editing mode, saving user changes,
    updating the user's name and profile image URI.
    This interface facilitates communication between the profile screen and its associated view model,
    enabling seamless user interaction and data management.
 */
interface ProfileEditActions {
    fun startEditing()

    fun saveUser()

    fun updateUserName(newName: String)

    fun updateImgUri(newUri: Uri?)

    fun cancelEditing()
}