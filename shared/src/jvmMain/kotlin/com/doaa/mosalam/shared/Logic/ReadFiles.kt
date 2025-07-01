import com.doaa.mosalam.shared.model.Student
import java.io.File

class ReadFiles {
    fun readAllCSVFile(filePath: String): MutableList<Student> {
        val students = mutableListOf<Student>()
        val file = File(filePath)

        if (!file.exists() || !file.isFile || file.extension.lowercase() != "csv") {
            println("❌ الملف غير موجود أو ليس ملف CSV: $filePath")
            return students
        }

        println("📄 قراءة الملف: ${file.name}")
        file.bufferedReader(Charsets.UTF_8).useLines { lines ->
            lines.drop(1).forEach { line ->
                val parts = line.split(Regex("[\t,;]"))
                if (parts.size >= 4) {
                    val name = parts[0].trim()
                    val seatNumber = parts[1].trim().toIntOrNull()
                    val secondLanguage = parts[2].trim()
                    val religion = parts[3].trim()

                    if (seatNumber != null) {
                        students.add(Student(name, seatNumber, secondLanguage, religion))
                    }
                }
            }
        }

        println("✅ تم تحميل ${students.size} طالبًا.")
        return students
    }

}