package com.example.ojt.Bullentin_Board.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDate
import javax.persistence.*


@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long = 1

    val name: String = " "

    val email: String = " "

    val phone: String = " "

    val type: String = " "

    val address: String = " "

    var password: String = ""
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    fun comparePassword(password : String) : Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    private val dob: LocalDate? = null

    constructor(id : Long,name:String,email:String,password: String,address:String,phone:String,type:String)

}




