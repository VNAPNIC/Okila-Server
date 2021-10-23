package com.okila.repository.storage

import java.io.File

object ImageUtils {

    fun getFileExtension(file: File): String? {
        val fileName: String = file.name
        return if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            fileName.substring(fileName.lastIndexOf(".") + 1)
        else ""
    }

    fun getFileExtension(fileName: String?): String? {
        return if (fileName?.lastIndexOf(".") != -1 && fileName?.lastIndexOf(".") != 0)
            fileName?.substring(fileName.lastIndexOf(".") + 1)
        else ""
    }
}