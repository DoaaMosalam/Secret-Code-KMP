package com.doaa.mosalam.composeApp.Ui

import ReadFiles
import SaveToCSV
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import assignGroups
import assignGroupsBySeatRanges
import assignOrderedSecretCodes
import assignRandomSecretCodes
import assignShuffledOrderedGroups
import com.doaa.mosalam.composeApp.compose.AppColors
import com.doaa.mosalam.composeApp.compose.ShowDialogeAlert
import com.doaa.mosalam.shared.compose.DropdownSelector
import com.doaa.mosalam.shared.compose.LabeledFilePicker
import org.jetbrains.compose.ui.tooling.preview.Preview
import java.awt.Desktop
import java.io.File

@Composable
@Preview
fun HomeApp() {

    var studentFilePath by remember { mutableStateOf("") }
    var outputFilePath by remember { mutableStateOf("") }
    var secretStartCode by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("English") }

    var mode by remember { mutableStateOf("Random Code") }
    var manualRangeFrom by remember { mutableStateOf("") }
    var manualRangeTo by remember { mutableStateOf("") }
    var groupSize by remember { mutableStateOf("") }

    val labels = getLocalizedLabels(language)
    val showSuccessDialog = remember { mutableStateOf(false) }
    var showWarningDialog by remember { mutableStateOf(false) }


    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .background(AppColors.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = labels["title"] ?: "",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.background
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = labels["language"] ?: "", color = Color.DarkGray)
                Spacer(modifier = Modifier.width(8.dp))
                DropdownSelector(
                    options = listOf("English", "Arabic"),
                    selected = language,
                    onOptionSelected = { language = it },

                )
            }

            Box(modifier = Modifier.border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)).padding(8.dp)) {
                LabeledFilePicker(
                    label = labels["chooseFile"] ?: "",
                    filePath = studentFilePath,
                    onPathChange = { studentFilePath = it },
                    isSaveDialog = false
                )
            }

            Box(modifier = Modifier.border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)).padding(8.dp)) {
                LabeledFilePicker(
                    label = labels["saveFile"] ?: "",
                    filePath = outputFilePath,
                    onPathChange = { outputFilePath = it },
                    isSaveDialog = true
                )
            }

            Text(text = labels["startCode"] ?: "", color = Color.DarkGray)
            TextField(
                value = secretStartCode,
                onValueChange = { secretStartCode = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Text(text = labels["mode"] ?: "", color = Color.DarkGray)
            DropdownSelector(
                options = listOf("Random Code", "Manual Grouping", "Auto Grouping"),
                selected = mode,
                onOptionSelected = { mode = it }
            )

            when (mode) {
                "Manual Grouping" -> {
                    Text(text = labels["manualFrom"] ?: "", color = Color.DarkGray)
                    TextField(
                        value = manualRangeFrom,
                        onValueChange = { manualRangeFrom = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(text = labels["manualTo"] ?: "", color = Color.DarkGray)
                    TextField(
                        value = manualRangeTo,
                        onValueChange = { manualRangeTo = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                "Auto Grouping" -> {
                    Text(text = labels["groupSize"] ?: "", color = Color.DarkGray)
                    TextField(
                        value = groupSize,
                        onValueChange = { groupSize = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.executeButton),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (studentFilePath.isBlank() || outputFilePath.isBlank()) {
                        showWarningDialog = true
                        return@Button
                    }
                    val students = ReadFiles().readAllCSVFile(studentFilePath)
                    if (students.isEmpty()) return@Button
                    val startCode = secretStartCode.toIntOrNull() ?: 501
                    val outputPath = outputFilePath

                    when (mode) {
                        "Auto Grouping" -> {
                            val size = groupSize.toIntOrNull() ?: return@Button
                            val result = assignShuffledOrderedGroups(
                                students = students,
                                groupSize = size,
                                startSecretCode = startCode
                            )
                            SaveToCSV().saveToCSV(result, outputPath)
                        }
                        "Manual Grouping" -> {
                            val from = manualRangeFrom.toIntOrNull() ?: return@Button
                            val to = manualRangeTo.toIntOrNull() ?: return@Button
                            val map = mapOf((from..to) to 1)
                            assignGroupsBySeatRanges(students, map)
                            assignOrderedSecretCodes(students, startCode)
                            SaveToCSV().saveToCSV(students, outputPath)
                        }
                        else -> {
                            assignGroups(students, 5)
                            assignRandomSecretCodes(students)
                            SaveToCSV().saveToCSV(students, outputPath)
                        }
                    }
                    println("✅ سيتم عرض الـ Dialog")
                  showSuccessDialog.value = true // ✅
}) {
    Text(text = labels["execute"] ?: "")
}

ShowDialogeAlert(showDialog = showSuccessDialog, language = language, outputFilePath = outputFilePath)
            }
        }

}

fun getLocalizedLabels(language: String): Map<String, String> {
    return if (language == "Arabic") mapOf(
        "title" to "\uD83D\uDD10 تقسيم الطلاب و توليد الأكواد السرية",
        "chooseFile" to "\uD83D\uDCC1 اختيار ملف الطلاب:",
        "saveFile" to "\uD83D\uDCBE مكان حفظ الملف الناتج:",
        "startCode" to "\uD83D\uDD22 بداية الرقم السري:",
        "mode" to "\uD83D\uDEE0️ اختر الوضع:",
        "manualFrom" to "من رقم جلوس:",
        "manualTo" to "إلى رقم جلوس:",
        "groupSize" to "عدد الطلاب في كل مجموعة:",
        "execute" to "✅ تنفيذ",
        "language" to "\uD83C\uDF10 اللغة:"
    ) else mapOf(
        "title" to "\uD83D\uDD10 Student Grouping & Secret Code Generator",
        "chooseFile" to "\uD83D\uDCC1 Choose Student File:",
        "saveFile" to "\uD83D\uDCBE Output File Location:",
        "startCode" to "\uD83D\uDD22 Secret Code Start:",
        "mode" to "\uD83D\uDEE0️ Choose Mode:",
        "manualFrom" to "From Seat Number:",
        "manualTo" to "To Seat Number:",
        "groupSize" to "Students per Group:",
        "execute" to "✅ Execute",
        "language" to "\uD83C\uDF10 Language:"
    )
}






