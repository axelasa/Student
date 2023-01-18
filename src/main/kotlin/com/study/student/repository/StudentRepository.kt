package com.study.student.repository

import com.study.student.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface StudentRepository:JpaRepository<StudentEntity,Long>{
    fun findByUserName(name:String):Optional<StudentEntity>
    fun findBySubject(name: String):Optional<StudentEntity>
    override fun findById(id:Long):Optional<StudentEntity>
    fun findByUserNameAndId(name: String,id: Long):Optional<StudentEntity>
}