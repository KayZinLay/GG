package com.example.ojt.Bullentin_Board.service

import com.example.ojt.Bullentin_Board.entity.Task
import com.example.ojt.Bullentin_Board.helper.CSVHelper
import com.example.ojt.Bullentin_Board.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream

@Service
class TaskServiceImpl : TaskService {

    @Autowired
    lateinit var taskRepository: TaskRepository

    override fun fetchAll(): List<Task>
    {
        return taskRepository.getAllTaskList()
    }

    override fun save(task: Task): List<Task> {
        taskRepository.save(task)
        return taskRepository.findAll()
    }

    override fun load(): ByteArrayInputStream {
        val employees = fetchAll()
        return CSVHelper.taskToCSV(employees)

    }

    override fun getAllTaskList(): List<Task> {
        return taskRepository.getAllTaskList()
    }

    override fun getTaskById(id: Int): Task {
        return taskRepository.getTaskById(id)
    }

    override fun deleteById(@Param("id") id: Int) {
        val task = taskRepository.getTaskById(id)
        return taskRepository.delete(task)

    }

    override fun searchByTask(data: String): List<Task> {
        return taskRepository.searchByTask(data)
    }

}