package com.doaa.mosalam.composeApp

import androidx.compose.material.Icon
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.doaa.mosalam.composeApp.Ui.HomeApp
import java.awt.Toolkit

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "Student Grouping",
        state = rememberWindowState(width = 900.dp, height = 800.dp),
        resizable = true,
        onPreviewKeyEvent = { event ->
            if (event.key == androidx.compose.ui.input.key.Key.Escape) {
                true
            } else {
                false
            }
        }
    ) {

        // 👇  WindowScope
        val icon = Toolkit.getDefaultToolkit().getImage("src/commonMain/composeResources/drawable/icon.png")
        window.iconImage = icon


        // val iconImage = ImageIO.read(File("src/main/resources/icon.png"))
        // window.iconImage = iconImage

        HomeApp()
    }
//    generateCode()
}