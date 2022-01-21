package com.example.ojt.Bullentin_Board.repository

import com.example.ojt.Bullentin_Board.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Int> {
}