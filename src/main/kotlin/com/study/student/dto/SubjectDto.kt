package com.study.student.dto

import com.study.student.entity.SubjectEntity

data class SubjectDto(val id:Long,val courseName:String,val Subjects:Set<String>){

    companion object{
        fun fromSubjectEntity(su:SubjectEntity):SubjectDto{
            return SubjectDto(su.id!!,su.courseName, su.subjects)
        }
    }
}
