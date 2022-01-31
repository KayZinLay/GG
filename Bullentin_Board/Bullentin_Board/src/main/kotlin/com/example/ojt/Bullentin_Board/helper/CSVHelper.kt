package com.example.ojt.Bullentin_Board.helper

import com.example.ojt.Bullentin_Board.entity.Task
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.csv.CSVRecord
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.lang.Byte.TYPE
import java.util.*


object CSVHelper {
    fun taskToCSV(tasks: List<Task>): ByteArrayInputStream {
        val format = CSVFormat.DEFAULT.withHeader("ID", "Post Title", "Post Description",
            "Posted User", "Posted Date")
        try {
            ByteArrayOutputStream().use({ out ->
                CSVPrinter(
                    PrintWriter(out),
                    format
                ).use { csvPrinter ->
                    for (task in tasks) {
                        val data: List<String?> = Arrays.asList(
                            task.id.toString(),
                            task.title, task.description,java.lang.String.valueOf(task.create_user_id),
                            java.lang.String.valueOf(task.created_at)
                        )
                        csvPrinter.printRecord(data)
                    }
                    csvPrinter.flush()
                    return ByteArrayInputStream(out.toByteArray())
                }
            })
        } catch (e: IOException) {
            throw RuntimeException(
                "fail to import data to CSV file: "
                        + e.message
            )
        }
    }

    fun csvToTasks(`is`: InputStream?): List<Task> {
        println("csvToTasks")
        try {
            BufferedReader(InputStreamReader(`is`, "UTF-8")).use { fileReader ->
                CSVParser(
                    fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
                ).use { csvParser ->
                    val tasks: MutableList<Task> =
                        ArrayList<Task>()
                    val csvRecords: Iterable<CSVRecord> = csvParser.getRecords()
                    print(csvRecords)
                    for (csvRecord in csvRecords) {
                        val task = Task(
                            csvRecord["Id"].toInt(),
                            csvRecord["Post Title"],
                            csvRecord["Post Description"],
                            csvRecord["Posted User"].toInt(),
                            csvRecord["Posted Date"]
                        )

//                    for (csvRecord in csvRecords) {
//                        val developerTutorial = Task(
//                            csvRecord["Id"].toInt(),
//                            csvRecord["Title"],
//                            csvRecord["Description"],
//                            csvRecord["user_create_id"].toInt(),
//                            csvRecord["created_at"].toInt()
//
//                        )
                        tasks.add(task)
                    }
                    return tasks
                }
            }
        } catch (e: IOException) {
            throw RuntimeException("fail to parse CSV file: " + e.message)
        }
    }

    fun hasCSVFormat(file: MultipartFile): Boolean {
        if (!TYPE.equals(file.contentType)) {
            println("not found")
            return false
        }
        return true
    }

}