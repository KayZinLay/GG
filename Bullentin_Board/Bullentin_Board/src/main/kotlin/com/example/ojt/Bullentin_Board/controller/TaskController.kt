package com.example.ojt.Bullentin_Board.controller

import com.example.ojt.Bullentin_Board.dto.TaskRequest
import com.example.ojt.Bullentin_Board.entity.Task
import com.example.ojt.Bullentin_Board.entity.User
import com.example.ojt.Bullentin_Board.repository.TaskRepository
import com.example.ojt.Bullentin_Board.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = arrayOf("http://localhost:8080"))
class TaskController(private val taskRepository: TaskRepository) {

    @Autowired
    var taskService: TaskService? = null

    @GetMapping("/tasks")
    fun getAllTaskList(@RequestParam(value="title", required = false, defaultValue = "") titleFilter: String,
                       @RequestParam(value="description", required = false, defaultValue = "") descriptionFilter: String,) : List<Task> {
        return taskRepository.findAll().filter {it.title.contains(titleFilter,true)
            it.description.contains(descriptionFilter, true) }
    }

    /*
       Search Task By Id
    */
    @GetMapping("/tasks/{id}")
    fun retrieveTask(@PathVariable(value = "id") id: Int): ResponseEntity<Task> {
        return taskRepository.findById(id).map { task ->
            ResponseEntity.ok(task)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/save_task")
    fun saveTask(@RequestBody task: Task) : Task =
        taskRepository.save(task)


//    fun updatePostById(@PathVariable(value = "id") id : Int,
//                          @RequestBody newTask: Task): ResponseEntity<Task> {
//
////        return taskRepository.findById(id).map { existingTask ->
////            val updatedTask: Task = existingTask
////                .copy(title = newTask.title, description = newTask.description)
////            ResponseEntity.ok().body(taskRepository.save(updatedTask))
////        }.orElse(ResponseEntity.notFound().build())
//
//    }

    @PutMapping("/tasks/{id}")
    fun updateTaskById(@PathVariable("id") id: Int, @RequestBody request: TaskRequest): Task {
        val task = taskRepository.existsById(id)
        return taskRepository.save(
                Task(
                    id = id,
                    title = request.title,
                    description = request.description,
                    updated_user_id = request.updated_user_id,
                    updated_at = LocalDateTime.now()
                )
            )
    }

    @DeleteMapping("/tasks/{id}")
    fun deleteUserById(@PathVariable(value = "id") id: Int):
            ResponseEntity<Void> {
        return taskRepository.findById(id).map { task ->
            taskRepository.delete(task)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }

    @GetMapping("/exportCSV")
    fun exportCSV(response: HttpServletResponse?):
            ResponseEntity<InputStreamResource> {
        val filename = "task.csv"
        val file = InputStreamResource(taskService!!.load())
        return ResponseEntity.ok().header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=$filename"
        )
            .contentType(MediaType.parseMediaType("application/csv")).body(file)
    }
}