package com.example.ojt.Bullentin_Board.service

import com.example.ojt.Bullentin_Board.entity.User
import com.example.ojt.Bullentin_Board.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun getAllTaskList(): List<User> {
        return userRepository.getAllUserList()
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun getById(id: Long): User {
        return userRepository.getById(id.toInt())
    }

    override fun save(user: User): List<User> {
        userRepository.save(user)
        return userRepository.findAll()
    }

    override fun deleteById(id: Long) {
        val user = userRepository.getById(id.toInt())
        return userRepository.delete(user)
    }

    override fun searchByUser(name: String, email: String): List<User>? {
        return userRepository.searchByUser(name,email)
    }

}