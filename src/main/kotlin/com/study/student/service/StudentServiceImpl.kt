package com.study.student.service

import com.study.student.entity.StudentEntity
import com.study.student.entity.SubjectEntity
import com.study.student.exceptions.ControllerExceptionHandler
import com.study.student.global.GlobalServices.Companion.subjectService
import com.study.student.model.EnrollForm
import com.study.student.model.StudentModel
import com.study.student.model.SubjectModel
import com.study.student.model.UpdatedStudent
import com.study.student.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class StudentServiceImpl (private var studentRepo:StudentRepository): StudentService {
    override fun createStudent(student: StudentModel): StudentEntity {
        val existingStudent = getStudent(student.userName)
        if (existingStudent.isPresent)throw ControllerExceptionHandler.conflicts("This student Exists")
        var students = StudentEntity.newStudent(student)
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

    override fun getSubject(name: SubjectEntity): Set<StudentEntity> {
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

    override fun enrollCourse(enrollForm: EnrollForm) {

        val existingUser  = getStudent(enrollForm.username)

        if(existingUser.isEmpty) throw ControllerExceptionHandler.notFound("This student does not exist")
        if (existingUser.get().subject != null) throw ControllerExceptionHandler.conflicts("This Student already has a course")
        val existingCourse = subjectService.getCourse(enrollForm.courseName)

        if (existingCourse.isEmpty) throw  ControllerExceptionHandler.notFound("This course does not exist")

        existingUser.get().subject = existingCourse.get()
        existingCourse.get().student.add(existingUser.get())

    }
}