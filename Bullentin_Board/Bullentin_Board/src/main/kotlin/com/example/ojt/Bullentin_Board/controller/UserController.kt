package com.example.ojt.Bullentin_Board.controller

import com.example.ojt.Bullentin_Board.dto.UserRequest
import com.example.ojt.Bullentin_Board.entity.User
import com.example.ojt.Bullentin_Board.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1" )
@CrossOrigin(origins = arrayOf("http://localhost:8080"))
class UserController (private  val userRepository: UserRepository){
    
    /*
         get all user lists
     */
    @GetMapping("/users")
    fun getAllUserList(@RequestParam(value="name", required = false, defaultValue = "") nameFilter: String,
                       @RequestParam(value="email", required = false, defaultValue = "") emailFilter: String,
                       @RequestParam(value="created_at", required = false, defaultValue = "") createdAtFilter: String,
                       @RequestParam(value="updated_at", required = false, defaultValue = "") UpdatedAtFilter: String,) : List<User> {

                return userRepository.findAll().filter {it.name.contains(nameFilter,true)
                                                        it.email.contains(emailFilter, true) }
    }

    /*
        Search User By Id
     */
    @GetMapping("/searchById/{id}")
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
    fun updatePatient(@RequestBody request: UserRequest, @PathVariable("id") id: Int): ResponseEntity<User> {
        val user = userRepository.findOneById(id)
        val updateUser = userRepository.save(User(
//            id = user.id,
//            name = request.name,

//            id = user.id,
//            name = request.name,
//            email = request.email,
//            phone = request.phone,
//            address = request.address,
//            dob = request.dob,
//            type = request.type,
//            updated_at = LocalDateTime.now()
        ))
        return ResponseEntity.ok(updateUser)
    }


//    @PutMapping("/users/{id}")
//    fun updateUserById(@PathVariable(value = "id") id : Int,
//                          @RequestBody newUser: User): ResponseEntity<User> {
//
////        return userRepository.findById(id).map { existingUser ->
////            val updatedUser: User = existingUser
////                .copy(name = newUser.name, dayOfBirth = newUser.dayOfBirth, email = newUser.email, address = newUser.address, phone = newUser.phone,
////                type = newUser.type, photo = newUser.photo)
////            ResponseEntity.ok().body(userRepository.save(updatedUser))
////        }.orElse(ResponseEntity.notFound().build())
//
//    }

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

}
