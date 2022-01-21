package com.example.ojt.Bullentin_Board.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.sql.Date
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0

    val name: String = " "

    val email: String = " "

    var password: String = ""
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    val profile_photo: String = ""

    @JsonFormat(pattern = "MM-dd-yyyy")
    lateinit var dob: Date

    val address: String = " "

    val phone: String = " "

    val type: String = " "

    val created_user_id: Int = 0

    val updated_user_id: Int = 0

    val deleted_user_id: Int = 0

    val created_at: LocalDateTime? = LocalDateTime.now()

    val updated_at: LocalDateTime? = LocalDateTime.now()

    val deleted_at: LocalDateTime? = LocalDateTime.now()

    fun comparePassword(password : String) : Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

}