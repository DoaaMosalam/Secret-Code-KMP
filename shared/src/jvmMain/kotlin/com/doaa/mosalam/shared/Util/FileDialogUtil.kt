package com.doaa.mosalam.shared.Util

import java.io.File

object FileDialogUtil {
    fun pickFile(): File? {
        val fileDialog = java.awt.FileDialog(null as java.awt.Frame?, "اختر ملف الطلاب", java.awt.FileDialog.LOAD)
        fileDialog.isVisible = true
        return if (fileDialog.file != null) File(fileDialog.directory, fileDialog.file) else null
    }

    fun saveFile(): File? {
        val fileDialog = java.awt.FileDialog(null as java.awt.Frame?, "حدد مكان حفظ الملف", java.awt.FileDialog.SAVE)
        fileDialog.isVisible = true
        return if (fileDialog.file != null) File(fileDialog.directory, fileDialog.file) else null
    }
}

