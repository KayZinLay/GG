package com.example.ojt.Bullentin_Board.repository

import com.example.ojt.Bullentin_Board.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Int> {

    @Query("SELECT * FROM post WHERE  title like %?1% or description like %?1% or status = '1'", nativeQuery = true )
    fun searchByTask(data: String): List<Task>

    @Query("SELECT * from post WHERE status = '1'", nativeQuery = true )
    fun getAllTaskList(): List<Task>

}