package com.study.student.model

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class SubjectModel (@field:NotEmpty(message = "Course is required") val courseName:String,@field: NotNull(message = "All students require subjects") val subject:List<String>)
