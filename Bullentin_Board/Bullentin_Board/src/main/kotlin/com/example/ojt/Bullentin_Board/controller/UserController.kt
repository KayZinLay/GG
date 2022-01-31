package com.example.ojt.Bullentin_Board.controller

import com.example.ojt.Bullentin_Board.dto.UserRequest
import com.example.ojt.Bullentin_Board.entity.User
import com.example.ojt.Bullentin_Board.repository.UserRepository
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/v1" )
@CrossOrigin(origins = arrayOf("http://localhost:8080"))
class UserController (private  val userRepository: UserRepository){
    
    /*
         get all user lists
     */
    @GetMapping("/users")
    fun getAllUserList() : List<User> {
        return userRepository.findAll()
    }

    /*
        Search User By Id
     */
    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable(value = "id") id: Int): ResponseEntity<User> {
        return userRepository.findById(id).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    /*
        Store New User
     */
    @PostMapping("/user_save")
    fun saveUser(@RequestBody user: User) : User =
        userRepository.save(user)

    @PutMapping("/users/{id}")
    fun updateUser(@PathVariable("id") id: Int, @RequestBody request: UserRequest): User {
        val user = userRepository.existsById(id)
        return userRepository.save(
            User(
                id = id,
                name = request.name,
                email = request.email,
                phone = request.phone,
                address = request.address,
                password = request.password,
                dob = request.dob,
                profile_photo = request.profile_photo,
                updated_user_id = request.updated_user_id,
                type = request.type,
                updated_at = LocalDateTime.now()
            )
        )
    }

    /*
        Delete User By Id
     */
    @DeleteMapping("/user_delete/{id}")
    fun deleteUserById(@PathVariable(value = "id") id: Int): ResponseEntity<Void> {

        return userRepository.findById(id).map { user  ->
            userRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }

    /*
        Search User
     */
    @GetMapping("/search_users")
    fun getUserListBySearchData(
        @RequestParam(value = "name", defaultValue = "") name: String,
        @RequestParam(value = "email", defaultValue = "") email: String,
        ): List<User>? {

        return  userRepository.searchByUser(name,email)
    }
}
