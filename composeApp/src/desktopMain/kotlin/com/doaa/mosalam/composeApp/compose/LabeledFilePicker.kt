package com.doaa.mosalam.shared.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.doaa.mosalam.composeApp.Util.FileDialogUtil

/*
*
* Text(text["chooseFile"] ?: "", fontWeight = FontWeight.SemiBold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = studentFilePath,
                        onValueChange = { studentFilePath = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("students.csv") }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        val file = FileDialogUtil.pickFile()
                        if (file != null) studentFilePath = file.absolutePath
                    }) { Text("...") }
                }

*  */
@Composable
fun LabeledFilePicker(
    label: String,
    filePath: String,
    onPathChange: (String) -> Unit,
    isSaveDialog: Boolean = false
) {
    Column {
        Text(text = label, color = Color.DarkGray,fontWeight = FontWeight.SemiBold)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = filePath,
                onValueChange = onPathChange,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                placeholder = { Text("Select a file", color = Color.Gray) }
            )
            Button(
                onClick = {
                    val selectedFile = if (isSaveDialog) {
                        FileDialogUtil.saveFile()
                    } else {
                        FileDialogUtil.pickFile()
                    }
                    if (selectedFile != null) {
                        onPathChange(selectedFile.absolutePath)
                    }

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFBDBDBD))
            ) {
                Text("...", color = Color.Black)
            }
        }
    }
}
