package com.doaa.mosalam.composeApp

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.doaa.mosalam.composeApp.Ui.HomeApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Student Grouping",
        state = rememberWindowState(width = 800.dp, height =750.dp)
    ) {
        HomeApp() // ← واجهة المستخدم
    }
    /*
     * folder path
     * */
//    generateCode()

}