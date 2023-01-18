package com.study.student.dto

import com.study.student.entity.StudentEntity
import java.util.Date

data class StudentDto(val userName:String,val firstname:String,val lastname:String,val age:Date,val subject:SubjectDto?,val updatedAt:Date,val createdAt:Date)
{
    companion object{
        fun fromStudentEntity(s:StudentEntity):StudentDto{

            return StudentDto(s.userName,s.firstname,s.lastName,s.age, if (s.subject != null) SubjectDto.fromSubjectEntity(
                s.subject!!) else null, s.updatedAt,s.createdAt)
        }
    }
}
