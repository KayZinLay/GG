package com.example.ojt.Bullentin_Board.helper

import com.example.ojt.Bullentin_Board.entity.Task
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.PrintWriter
import java.util.*

object CSVHelper {
    fun employeesToCSV(tasks: List<Task>): ByteArrayInputStream {
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
}