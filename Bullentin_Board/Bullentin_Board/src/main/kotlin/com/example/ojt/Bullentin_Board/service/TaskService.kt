package com.example.ojt.Bullentin_Board.service

import com.example.ojt.Bullentin_Board.entity.Task
import com.example.ojt.Bullentin_Board.helper.CSVHelper
import com.example.ojt.Bullentin_Board.repository.TaskRepository
import com.example.ojt.Bullentin_Board.repository.UserRepository
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream

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
        return CSVHelper.employeesToCSV(employees)
    }
}