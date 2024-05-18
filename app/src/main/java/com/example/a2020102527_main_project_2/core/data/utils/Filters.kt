package com.example.a2020102527_main_project_2.core.data.utils
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/


/*
    The RunSortOrder enum defines different sorting criteria for runs,
    including date, duration, calories burned, average speed, and distance.
    The toString() function overrides the default behavior to provide a more readable representation
    of each enum value. It converts the enum names to lowercase, replaces underscores with spaces,
    and capitalizes the first character of each word. This makes the sorting criteria more user-friendly
     when displayed in the UI or used in log messages or notifications.
 */
enum class RunSortOrder {
    DATE,
    DURATION,
    CALORIES_BURNED,
    AVG_SPEED,
    DISTANCE;

    override fun toString(): String {
        return super.toString()
            .lowercase()
            .replace('_', ' ')
            .replaceFirstChar { it.uppercase() }
    }
}