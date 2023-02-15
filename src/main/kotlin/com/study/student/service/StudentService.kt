package com.study.student.service

import com.study.student.entity.StudentEntity
import com.study.student.entity.SubjectEntity
import com.study.student.model.EnrollForm
import com.study.student.model.StudentModel
import com.study.student.model.UpdatedStudent
import jakarta.validation.Valid
import java.util.*

interface StudentService {
    fun createStudent(@Valid student:StudentModel):StudentEntity
    fun getStudent(name:String): Optional<StudentEntity>
    fun updateStudent(student: UpdatedStudent):StudentEntity
    fun getSubject(name:SubjectEntity):Set<StudentEntity>
    fun getAllStudents():List<StudentEntity>
    fun getStudentsById(id:Long):Optional<StudentEntity>
    fun getStudentByUniqueValues(name: String,id: Long):Optional<StudentEntity>

    fun enrollCourse(enrollForm: EnrollForm)
}