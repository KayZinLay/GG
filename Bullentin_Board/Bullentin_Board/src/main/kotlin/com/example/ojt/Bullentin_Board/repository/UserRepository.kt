package com.example.ojt.Bullentin_Board.repository

import com.example.ojt.Bullentin_Board.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {

    @Query("select * from user where name like %?1%", nativeQuery = true)
    fun search(s: String): List<User>?

    fun findByEmail(email: String): User?

    fun findOneById(id: Int): User

}