package com.example.ojt.Bullentin_Board.repository

import com.example.ojt.Bullentin_Board.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface UserRepository : JpaRepository<User, Int> {

    fun findByEmail(email: String): User?

    @Query("select * from users where name like %?1% or email like %?2%", nativeQuery = true )
    fun searchByUser(name: String, email: String): List<User>

}