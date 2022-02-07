package com.example.ojt.Bullentin_Board.service

import com.example.ojt.Bullentin_Board.entity.User
import org.springframework.stereotype.Service

@Service
interface UserService {

    fun getAllTaskList(): List<User>

    fun findByEmail(email: String) : User?

    fun getById(id: Long): User

    fun save(user: User): List<User>

    fun deleteById(id: Long)

    fun searchByUser(name: String, email: String): List<User>?

}