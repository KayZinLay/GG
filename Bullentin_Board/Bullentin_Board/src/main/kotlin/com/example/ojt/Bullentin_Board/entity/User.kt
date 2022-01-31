package com.example.ojt.Bullentin_Board.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 1,

    var name: String = "",

    var email: String = "",

    var password: String = "",

    var profile_photo: String = "",

    @JsonFormat(pattern = "MM-dd-yyyy")
    var dob: LocalDate,

    var address: String = " ",

    var phone: String = " ",

    var type: String = " ",

    var created_user_id: Int = 0,

    var updated_user_id: Int = 0,

    var deleted_user_id: Int = 0,

    var created_at: LocalDateTime? = LocalDateTime.now(),

    var updated_at: LocalDateTime? = LocalDateTime.now(),

    var deleted_at: LocalDateTime? = LocalDateTime.now(),

){
    fun comparePassword(password : String) : Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

//    get() = field
//    set(value) {
//        val passwordEncoder = BCryptPasswordEncoder()
//        field = passwordEncoder.encode(value)
//    }
}

