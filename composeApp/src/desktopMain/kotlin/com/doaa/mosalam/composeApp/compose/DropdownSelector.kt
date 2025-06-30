package com.doaa.mosalam.shared.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import com.doaa.mosalam.composeApp.Util.AppColors

@Composable
fun DropdownSelector(options: List<String>, selected: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Button(onClick = { expanded = true },
//            modifier = Modifier.background(AppColors.btnRandomandLan),
//            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.btnRandomandLan)
        )

        {
            Text(selected)
            ButtonDefaults.buttonColors(backgroundColor = AppColors.btnRandomandLan)

        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(option)
                    expanded = false
                }) {
                    Text(option)
                }
            }
        }
    }

}

