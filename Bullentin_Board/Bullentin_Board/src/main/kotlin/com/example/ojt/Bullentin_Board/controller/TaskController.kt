package com.example.ojt.Bullentin_Board.controller

import com.example.ojt.Bullentin_Board.dto.Message
import com.example.ojt.Bullentin_Board.dto.TaskRequest
import com.example.ojt.Bullentin_Board.entity.Task
import com.example.ojt.Bullentin_Board.helper.CSVHelper.hasCSVFormat
import com.example.ojt.Bullentin_Board.repository.TaskRepository
import com.example.ojt.Bullentin_Board.service.CsvService
import com.example.ojt.Bullentin_Board.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = arrayOf("http://localhost:8080"))
class TaskController(private val csvService: CsvService) {

    @Autowired
    var taskService: TaskService? = null

    @GetMapping("/tasks")
    fun getAllTaskList() : List<Task> {
        return taskService!!.getAllTaskList()
    }

    /*
     Search Task By Id
    */
    @GetMapping("/tasks/{id}")
    fun retrieveTask(@PathVariable(value = "id") id: Int):
            Task {
        return taskService!!.getTaskById(id)
    }

    /*
        save task
     */
    @PostMapping("/save_task")
    fun saveTask(@RequestBody task: Task) : List<Task> =
        taskService!!.save(task)

    /*
        update task by id
     */
    @PutMapping("/tasks/{id}")
    fun updateUserById(@PathVariable("id") id: Int, @RequestBody request: TaskRequest): List<Task> {
        return taskService!!.save(
                Task(
                    id = id,
                    title = request.title,
                    description = request.description,
                    updated_at = LocalDateTime.now()
                )
        )
    }

    /*
        delete task by id
     */
    @DeleteMapping("/tasks/{id}")
    fun deleteTaskById(@PathVariable("id") id: Int) {
        return taskService!!.deleteById(id)
    }

    /*
        download the task csv file
     */
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

    /*
        upload Task CSV file
     */
    @PostMapping("/uploadCSV")
    fun uploadCSVFile(@RequestParam("file") file: MultipartFile) : ResponseEntity<Message> {
        println("file"+file)
        var message = ""
        try {
            csvService.save(file)

            message = "uploaded the file successfully:" + file.originalFilename;
            return ResponseEntity.status(HttpStatus.OK).body(Message(message));

        }catch(e: Exception){
            message = "Could not upload the file" + file.originalFilename + "!"
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Message(message))
        }
    }

    /*
        Search Task by title or description
     */
    @GetMapping("/search_tasks")
    fun getTaskListBySearchData(
        @RequestParam(value = "data", defaultValue = "") data: String,
    ) : List<Task> {
        return taskService!!.searchByTask(data)
    }

}


