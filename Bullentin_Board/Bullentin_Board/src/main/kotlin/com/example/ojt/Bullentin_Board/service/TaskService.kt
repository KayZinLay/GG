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
interface TaskService {

    fun fetchAll(): List<Task>

    fun save(task: Task): List<Task>

    fun load(): ByteArrayInputStream

    fun getAllTaskList(): List<Task>

    fun getTaskById(id: Int): Task

    fun deleteById(id: Int)

    fun searchByTask(data: String): List<Task>

}