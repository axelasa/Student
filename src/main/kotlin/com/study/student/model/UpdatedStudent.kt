package com.study.student.model

import jakarta.validation.constraints.NotEmpty

data class UpdatedStudent(@NotEmpty(message = "UserName Required") val userName:String,val firstname:String?,val lastname:String?,val subject:String?)
