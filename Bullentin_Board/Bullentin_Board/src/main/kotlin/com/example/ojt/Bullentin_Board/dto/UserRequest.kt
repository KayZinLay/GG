package com.example.ojt.Bullentin_Board.dto

import java.time.LocalDate

class UserRequest (
    val id: Int,
   val name : String,
    val email : String,
    val address : String,
    val phone : String,
    val dob : LocalDate,
    val type : String,
   val password:String

)