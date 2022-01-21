package com.example.ojt.Bullentin_Board.service

import com.example.ojt.Bullentin_Board.entity.User
import com.example.ojt.Bullentin_Board.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findByEmail(email: String) : User? {
        return this.userRepository.findByEmail(email)
    }

    fun getById(id: Int) : User {
        return  this.userRepository.getById(id)
    }
}