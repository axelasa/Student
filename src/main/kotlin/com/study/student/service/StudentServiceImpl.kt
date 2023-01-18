package com.study.student.service

import com.study.student.entity.StudentEntity
import com.study.student.entity.SubjectEntity
import com.study.student.exceptions.ControllerExceptionHandler
import com.study.student.model.StudentModel
import com.study.student.model.UpdatedStudent
import com.study.student.repository.StudentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentServiceImpl (private var studentRepo:StudentRepository, var subject:SubjectEntity ): StudentService {
    override fun createStudent(student: StudentModel): StudentEntity {
        val existingStudent = getStudent(student.userName)
        if (existingStudent.isPresent)throw ControllerExceptionHandler.conflicts("This student Exists")
        var students = StudentEntity.newStudent(student,subject)
        return studentRepo.save(students)
    }

    override fun getStudent(name: String): Optional<StudentEntity> {
        return studentRepo.findByUserName(name)
    }

    override fun updateStudent(student: UpdatedStudent): StudentEntity {
        val existingStudent = getStudent(student.userName)
        if (existingStudent.isEmpty)throw ControllerExceptionHandler.notFound("Student Not Found, Huyo ni nani?")
        var updatedStudent = existingStudent.get()

        if(student.firstname != null) updatedStudent.firstname = student.firstname
        if (student.lastname != null) updatedStudent.lastName = student.lastname
        //if (student.subject != null) updatedStudent.subject = student.subject
        updatedStudent.updatedAt = Date()
        return studentRepo.save(updatedStudent)

    }

    override fun getSubject(name: String): Optional<StudentEntity> {
        return studentRepo.findBySubject(name)
    }

    override fun getAllStudents():List<StudentEntity> {
        return studentRepo.findAll()
    }

    override fun getStudentsById(id: Long): Optional<StudentEntity> {
       return studentRepo.findById(id)
    }

    override fun getStudentByUniqueValues(name: String, id: Long): Optional<StudentEntity> {
        return studentRepo.findByUserNameAndId(name, id)
    }


}