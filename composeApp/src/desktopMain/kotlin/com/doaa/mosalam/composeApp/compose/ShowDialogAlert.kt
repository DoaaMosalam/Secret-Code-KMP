package com.doaa.mosalam.composeApp.compose

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import java.awt.Desktop
import java.io.File


@Composable
fun ShowDialogeAlert(
    showDialog: MutableState<Boolean>,
    language: String,
    outputFilePath: String,
) {
    if (showDialog.value) {
        val dialogTitle = if (language == "Arabic") "✅ تم التنفيذ" else "✅ Executed Successfully"
        val dialogText = if (language == "Arabic")
            "تم حفظ النتائج بنجاح. هل تريد فتح مكان الحفظ؟"
        else
            "Results saved successfully. Do you want to open the folder?"

        val openButtonText = if (language == "Arabic") "فتح المجلد" else "Open Folder"
        val closeButtonText = if (language == "Arabic") "إغلاق" else "Close"

        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(dialogTitle) },
            text = { Text(dialogText) },
            confirmButton = {
                Button(onClick = {
                    Desktop.getDesktop().open(File(outputFilePath).parentFile)
                    showDialog.value = false
                }) {
                    Text(openButtonText)
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog.value = false
                }) {
                    Text(closeButtonText)
                }
            }
        )
    }
}

@Composable
fun showWarningDialog(
    showWarningDialog: MutableState<Boolean>,
    language: String,
    outputFilePath: String,
){

    if (showWarningDialog.value) {
        AlertDialog(
            backgroundColor = AppColors.error,
            onDismissRequest = { showWarningDialog.value = false },
            title = {
                Text(if (language == "Arabic") "⚠️ تنبيه" else "⚠️ Warning")
            },
            text = {
                Text(
                    if (language == "Arabic")
                        "من فضلك اختر ملف الطلاب ومكان حفظ النتائج قبل التنفيذ."
                    else
                        "Please select both student file and output file path before executing."
                )
            },
            confirmButton = {

                Button(onClick = { showWarningDialog.value = false }) {
                    Text(if (language == "Arabic") "موافق" else "OK")
                }
            },
            dismissButton = {
                Button(onClick = { showWarningDialog.value = false }) {
                    Text(if (language == "Arabic") "إغلاق" else "Close")
                }
            }
        )
    }
}
