package com.study.student.service

import com.study.student.entity.StudentEntity
import com.study.student.entity.SubjectEntity
import com.study.student.exceptions.ControllerExceptionHandler
import com.study.student.global.GlobalServices.Companion.studentService
import com.study.student.model.StudentModel
import com.study.student.model.SubjectModel
import com.study.student.repository.StudentRepository
import com.study.student.repository.SubjectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class SubjectServiceImpl(var subjectRepo:SubjectRepository): SubjectService {
    override fun addSubjects(subject: SubjectModel): SubjectEntity {

        val existingSubject = getCourse(subject.courseName)
        if (existingSubject.isPresent) throw ControllerExceptionHandler.conflicts("These subjects exist")
        val newSubject = SubjectEntity.newSubjects(subject)
        return subjectRepo.save(newSubject)
    }

    override fun getCourse(name: String): Optional<SubjectEntity> {
        return subjectRepo.findByCourseName(name)
    }


}