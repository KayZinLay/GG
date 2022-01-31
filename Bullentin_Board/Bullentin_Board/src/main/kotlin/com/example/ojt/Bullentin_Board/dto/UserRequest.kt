package com.example.ojt.Bullentin_Board.dto

import java.time.LocalDate

class UserRequest (
   val name : String,
    val email : String,
    val address : String,
    val phone : String,
    val dob : LocalDate,
    val type : String,
    val profile_photo : String,
   val updated_user_id: Int,
   val password:String

)