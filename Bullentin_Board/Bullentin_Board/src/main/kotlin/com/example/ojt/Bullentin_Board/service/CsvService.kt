package com.example.ojt.Bullentin_Board.service

import antlr.StringUtils
import com.example.ojt.Bullentin_Board.entity.Task
import com.example.ojt.Bullentin_Board.exception.BadRequestException
import com.example.ojt.Bullentin_Board.exception.CsvImportException
import com.example.ojt.Bullentin_Board.helper.CSVHelper
import com.example.ojt.Bullentin_Board.repository.TaskRepository
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils.cleanPath
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


@Service
class CsvService {

    fun uploadCsvFile(file: MultipartFile): MutableList<Task>? {
        throwIfFileEmpty(file)
        var fileReader: BufferedReader? = null

        try {
            fileReader = BufferedReader(InputStreamReader(file.inputStream))
            val csvToBean = createCSVToBean(fileReader)

            return csvToBean.parse()
        } catch (ex: Exception) {
            throw CsvImportException("Error during csv import")
        } finally {
            closeFileReader(fileReader)
        }
    }

//    fun save(file: MultipartFile) {
//        try {
//            val tutorials: List<Task> = CSVHelper.employeesToCSV(file.inputStream)
//            taskRepository.saveAll(tutorials)
//        } catch (e: IOException) {
//            throw RuntimeException("fail to store csv data: " + e.message)
//        }
//    }

    private fun throwIfFileEmpty(file: MultipartFile) {
        if (file.isEmpty)
            throw BadRequestException("Empty file")
    }

    private fun createCSVToBean(fileReader: BufferedReader?): CsvToBean<Task> =
        CsvToBeanBuilder<Task>(fileReader)
            .withType(Task::class.java)
            .withIgnoreLeadingWhiteSpace(true)
            .build()

    private fun closeFileReader(fileReader: BufferedReader?) {
        try {
            fileReader!!.close()
        } catch (ex: IOException) {
            throw CsvImportException("Error during csv import")
        }
    }

    @Autowired
    lateinit var taskrepository: TaskRepository

    fun save(file: MultipartFile) {
        println("file save funciton")
        try {
            val task: List<Task> = CSVHelper.csvToTasks(file.inputStream)
            println("Task"+task)
            if (task != null) {
                taskrepository.saveAll(task)
            }
        } catch (e: IOException) {
            throw RuntimeException("fail to store csv data: " + e.message)
        }
    }

}