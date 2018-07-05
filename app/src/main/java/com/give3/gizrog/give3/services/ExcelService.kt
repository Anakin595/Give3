package com.give3.gizrog.give3.services

import android.util.Log
import com.give3.gizrog.give3.models.AssessmentSection
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileWriter

class ExcelService {




    companion object {

        fun exportAssessmentSectionsToCsv(assessmentSections: ArrayList<AssessmentSection>) {
            Log.d("exportService", "export: started.")

            val baseDir = android.os.Environment.getExternalStorageDirectory().absolutePath
            val fileName = "evidence_list.csv"
            val filePath: String = baseDir + File.separator + fileName
            val file = File(filePath)



            val writer: CSVWriter = if(file.exists() && !file.isDirectory) {
                CSVWriter(FileWriter(filePath, false))
            } else {
                CSVWriter(FileWriter(filePath))
            }

            val data = ArrayList<Array<String>>()
            assessmentSections.forEach {sections ->
                data.add(arrayOf(sections.section.title))
                sections.section.studentsNames.forEach {
                    data.add(arrayOf(it, sections.grade.toString()))
                }
            }

            writer.writeAll(data)
            writer.close()
            Log.d("exportService", "export: saved to $filePath.")
        }

    }

}