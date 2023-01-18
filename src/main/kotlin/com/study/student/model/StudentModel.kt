package com.study.student.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class StudentModel(@field:NotEmpty(message = "Username Cannot Be Blank") @field:NotEmpty(message = "Username Required")val userName:String, @field:NotEmpty(message = "Firstname Required")val firstname:String, @field:NotEmpty(message = "Lastname Required") val lastname:String, @field:NotNull(message = "Age cannot be null")val age:Date)
