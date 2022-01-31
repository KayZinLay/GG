package com.example.ojt.Bullentin_Board.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "post")
data class Task(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 1,

    var title: String = "",

    var description: String = "",

    var status: Int = 1,

    var create_user_id: Int = 1,

    var updated_user_id: Int = 1,

    var deleted_user_id: Int = 0,

    var created_at: LocalDateTime? = LocalDateTime.now(),

    var updated_at: LocalDateTime? = LocalDateTime.now(),

    var deleted_at: LocalDateTime = LocalDateTime.now()
) {
    constructor(id: Int, title: String?, description: String?, status: Int, create_user_id: String?) : this()
}