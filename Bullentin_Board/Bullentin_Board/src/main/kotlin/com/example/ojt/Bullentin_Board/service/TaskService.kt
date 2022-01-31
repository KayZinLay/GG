package com.example.ojt.Bullentin_Board.service

import com.example.ojt.Bullentin_Board.entity.Task
import com.example.ojt.Bullentin_Board.exception.BadRequestException
import com.example.ojt.Bullentin_Board.exception.CsvImportException
import com.example.ojt.Bullentin_Board.helper.CSVHelper
import com.example.ojt.Bullentin_Board.repository.TaskRepository
import com.example.ojt.Bullentin_Board.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStreamReader

@Service
class TaskService(private val taskRepository: TaskRepository) {

    fun fetchAll(): List<Task> {
        return taskRepository!!.findAll()
    }

    fun save(task: Task): Task {
        return taskRepository.save(task)
    }


    fun load(): ByteArrayInputStream {
        val employees = fetchAll()
        return CSVHelper.taskToCSV(employees)
    }
}