package com.doaa.mosalam.shared


import ReadFiles
import SaveToCSV
import androidx.compose.ui.window.application
import assignShuffledOrderedGroups

fun generateCode() = application {

    /**
    * Path to the folder containing the CSV files.
    * Make sure to use double backslashes (\\) or a single forward slash (/) in the path.
    * Example: "E:\\path\\to\\your\\folder" or "E:/path/to/your/folder"
* */

    val filePath = "E:\\Code Programming\\Kotlin\\committes Secret Code\\Commites\\Prep-2.csv"
    val outputPath = "E:\\Code Programming\\Kotlin\\committes Secret Code\\final_output.csv" //  save results

    val students = ReadFiles().readAllCSVFile(filePath)
    if (students.isEmpty()) return@application
    /*
    * generate secret codes and assign groups to students.
    * */

    val groupSize = 30
//    assignGroups(students, groupSize)
//
//    assignRandomSecretCodes(students)
//
//    assignOrderedSecretCodesStudy(students)

    val groupRanges = mapOf(
        181..196 to 1,
        1..20 to 2,
        161..180 to 3,
        21..40 to 4,
        141..160 to 5,
        41..60 to 6,
        121..140 to 7,
        61..80 to 8,
        101..120 to 9,
        81..100 to 10,

    )
//    assignGroupsBySeatRanges(students, groupRanges)
//    assignOrderedSecretCodes(students, startCode = 501)

    /*
* Sign Random student count Group.
* */

    assignShuffledOrderedGroups(
        students = students,
        groupSize = groupSize, // Number of students in each group,
        startSecretCode = 501
    )

    println("\n📋 النتائج:")
    println("الاسم, رقم الجلوس, المجموعة, الرقم السري")
    students.forEach {
        println("${it.name}, ${it.seatNumber}, ${it.groupNumber}, ${it.secretCode}")
    }

    SaveToCSV().saveToCSV(students, outputPath)
    println("\n✅ تم حفظ النتائج النهائية.")
}



