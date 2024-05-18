package com.example.a2020102527_main_project_2.utils
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import java.util.Calendar
import java.util.Date

/*
    These extension functions and utility methods enhance the functionality of the Calendar and Date classes in managing dates and times:

    Setters: Functions to set the calendar date to the first and last days of the week, along with methods to set the minimum and maximum time of a given date.

    Conversion: Converts a Date object to a Calendar instance.

    Iteration: Allows iterating through a range of Calendar instances, advancing one day at a time.

    Conversion to List: Converts a range of Calendar instances to a list for easy manipulation and usage.
 */
fun Calendar.setDateToWeekFirstDay() = apply {
    val firstDay = getActualMinimum(Calendar.DAY_OF_WEEK)
    set(Calendar.DAY_OF_WEEK, firstDay)
    setMinimumTime()
}

fun Calendar.setDateToWeekLastDay() = apply{
    val lastDay = getActualMinimum(Calendar.DAY_OF_WEEK)
    set(Calendar.DAY_OF_WEEK,lastDay)
    setMaximumTime()
}

fun Calendar.setMinimumTime() = apply{
    set(Calendar.HOUR_OF_DAY, getActualMinimum(Calendar.HOUR_OF_DAY))
    set(Calendar.MINUTE, getActualMinimum(Calendar.MINUTE))
    set(Calendar.SECOND, getActualMinimum(Calendar.SECOND))
    set(Calendar.MILLISECOND, getActualMinimum(Calendar.MILLISECOND))
}

fun Calendar.setMaximumTime() = apply{
    set(Calendar.HOUR_OF_DAY, getActualMaximum(Calendar.HOUR_OF_DAY))
    set(Calendar.MINUTE, getActualMaximum(Calendar.MINUTE))
    set(Calendar.SECOND, getActualMaximum(Calendar.SECOND))
    set(Calendar.MILLISECOND, getActualMaximum(Calendar.MILLISECOND))
}

fun Date.toCalendar(): Calendar = Calendar.getInstance().also{it.time = this}

operator fun ClosedRange<Calendar>.iterator() = object: Iterator<Calendar>{
    var current = start

    override fun hasNext(): Boolean {
        (1..2).toList()
        return current <= endInclusive
    }
    override fun next(): Calendar {
        val temp = current
        current = (current.clone() as Calendar).apply {
            add(Calendar.DAY_OF_WEEK, 1)
        }
        return temp
    }
}

fun ClosedRange<Calendar>.toList(): List<Calendar> = buildList{
    for(c in this@toList) add(c)
}