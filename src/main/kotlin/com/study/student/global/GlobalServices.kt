package com.study.student.global

import com.study.student.service.StudentService
import com.study.student.service.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GlobalServices{
    companion object{
        lateinit var studentService:StudentService
        lateinit var subjectService: SubjectService

    }
    @Autowired
    fun setStudentService(studentService: StudentService){
        GlobalServices.studentService = studentService
    }
    @Autowired
    fun setSubjectService(subjectService: SubjectService){
        GlobalServices.subjectService = subjectService
    }
}