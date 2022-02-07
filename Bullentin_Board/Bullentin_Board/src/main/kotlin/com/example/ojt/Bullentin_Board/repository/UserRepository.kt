package com.example.ojt.Bullentin_Board.repository

import com.example.ojt.Bullentin_Board.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {

    @Query("select * from users where email = ?1", nativeQuery = true )
    fun findByEmail(email: String): User?

    @Query("SELECT * FROM users  WHERE name = ?1 or email = ?2", nativeQuery = true)
    fun searchByUser(name: String, email: String): List<User>

    @Query("SELECT * from users", nativeQuery = true)
    fun getAllUserList(): List<User>

}