import com.doaa.mosalam.composeApp.model.Student
import java.io.File

class SaveToCSV {
    fun saveToCSV(students: List<Student>, fileName: String) {
        val file = File(fileName)
        val bom = "\uFEFF"
        file.outputStream().bufferedWriter(charset = Charsets.UTF_8).use { out ->
            out.write(bom)
            out.write("الاسم,رقم الجلوس,اللغة الثانية,الديانة,المجموعة,الرقم السري\n")
            students.forEach {
                if (it.groupNumber > 0) {
                    out.write("${it.name},${it.seatNumber},${it.secondLanguage},${it.religion},${it.groupNumber},${it.secretCode}\n")
                }
            }
        }
        println("✅ تم حفظ النتائج في الملف: $fileName")
    }

}
