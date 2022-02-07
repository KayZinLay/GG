package com.example.ojt.Bullentin_Board.controller

import com.example.ojt.Bullentin_Board.entity.User
import com.example.ojt.Bullentin_Board.repository.UserRepository
import com.example.ojt.Bullentin_Board.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1" )
@CrossOrigin(origins = arrayOf("http://localhost:8080"))
class UserController (private  val userRepository: UserRepository){

    @Autowired
    var userService: UserService? = null
    
    /*
         get all user lists
     */
    @GetMapping("/users")
    fun getAllUserList() : List<User> {
        return userService!!.getAllTaskList()
    }

    /*
        Search User By Id
     */
    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable(value = "id") id: Long): ResponseEntity<User> {
        return userRepository.findById(id.toInt()).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    /*
        Store New User
     */
    @PostMapping("/save")
    fun userSave(@RequestBody user: User) : List<User> =
        userService!!.save(user)

    /*
        update User By Id
     */
    @PutMapping("/users/{id}")
    fun updateUser(@PathVariable("id") id: Long, @RequestBody user: User): List<User> {
        return userService!!.save(user)

    }

    /*
        Delete User By Id
     */
    @DeleteMapping("/user_delete/{id}")
    fun deleteUserById(@PathVariable(value = "id") id: Long) {
        return userService!!.deleteById(id)
    }

    /*
        Search User
     */
    @GetMapping("/search_users")
    fun getUserListBySearchData(
        @RequestParam(value = "name", defaultValue = "", required = false) name: String,
        @RequestParam(value = "email", defaultValue = "", required = false) email: String,
        ): List<User>? {

        return userService!!.searchByUser(name,email)
    }
}
