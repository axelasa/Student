package com.study.student.model

data class ApiResponse(
    val status:Int,
    val description:String,
    val data:Any?
)
