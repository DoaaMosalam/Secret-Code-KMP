import com.doaa.mosalam.composeApp.model.Student
import kotlin.random.Random


/*
* A code that generates secret numbers for students randomly
*
* */

fun assignGroups(students: List<Student>, groupSize: Int) {
    students.forEachIndexed { index, student ->
        student.groupNumber = (index / groupSize) + 1
    }
}

fun assignRandomSecretCodes(students: List<Student>) {
    val usedCodes = mutableSetOf<Int>()
    students.forEach { student ->
        var code: Int
        do {
            code = Random.nextInt(1000, 9999)
        } while (!usedCodes.add(code))
        student.secretCode = code
    }
}
    /*
    * A code that generates secret numbers for students in a random, ordered manner,
    * starting from a specific number and increasing sequentially. The secret numbers vary for each group.
    * */

    fun assignOrderedSecretCodesStudy(students: List<Student>) {
        val groupToStudents = students.groupBy { it.groupNumber }
        for ((_, groupStudents) in groupToStudents) {
            var startCode = Random.nextInt(501, 1000 - groupStudents.size)
            val sortedGroup = groupStudents.sortedBy { it.seatNumber }
            for (student in sortedGroup) {
                student.secretCode = startCode++
            }
        }
    }


/*
* A code that generates secret numbers starting from a specific number
* and increasing sequentially across all groups,
*  not for each group individually.
* */

fun assignOrderedSecretCodes(students: List<Student>, startCode: Int = 1000) {
    val sortedStudents = students.sortedBy { it.groupNumber }
    var code = startCode
    for (student in sortedStudents) {
        student.secretCode = code++
    }
}

fun assignGroupsBySeatRanges(students: List<Student>, groupRanges: Map<IntRange, Int>) {
    for (student in students) {
        for ((range, group) in groupRanges) {
            if (student.seatNumber in range) {
                student.groupNumber = group
                break
            }
        }

    }
            val notAssigned = students.filter { it.groupNumber == 0 }
        if (notAssigned.isNotEmpty()) {
            println("⚠️ الطلاب الذين لم يتم تعيين مجموعة لهم:")
            notAssigned.forEach {
                println("${it.name}, رقم الجلوس: ${it.seatNumber}")
            }
        }
}
/*
* create random group student
*
* */

fun assignShuffledOrderedGroups(
    students: List<Student>,
    groupSize: Int,
    startSecretCode: Int
): List<Student> {
    val sortedStudents = students.sortedBy { it.seatNumber }
    val groups = sortedStudents.chunked(groupSize)
    val shuffledGroups = groups.shuffled()

    var currentSecretCode = startSecretCode
    var groupNumber = 1
    val finalList = mutableListOf<Student>()

    for (group in shuffledGroups) {
        for (student in group) {
            student.groupNumber = groupNumber
            student.secretCode = currentSecretCode
            currentSecretCode++
            finalList.add(student)
        }
        groupNumber++
    }

    return finalList.sortedBy { it.seatNumber } // ممكن تلغي السورت لو عايزة الترتيب النهائي حسب المجموعة
}



