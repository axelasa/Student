package com.study.student.service

import com.study.student.entity.StudentEntity
import com.study.student.entity.SubjectEntity
import com.study.student.exceptions.ControllerExceptionHandler
import com.study.student.model.SubjectModel
import com.study.student.repository.SubjectRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SubjectServiceImpl(var subjectRepo:SubjectRepository, var studentservice:StudentService): SubjectService {
    override fun addSubjects(subject: SubjectModel): SubjectEntity {

        val existingSubject = getCourse(subject.courseName)
        if (existingSubject.isPresent) throw ControllerExceptionHandler.conflicts("These subjects exist")
        val newSubject = SubjectEntity.newSubjects(subject,studentservice.)
        return subjectRepo.save(newSubject)
    }

    override fun getSubject(subject: Set<String>): Optional<List<SubjectEntity>> {
        return subjectRepo.findBySubjects(subject)
    }

    override fun getCourse(name: String): Optional<SubjectEntity> {
        return subjectRepo.findByCourseName(name)
    }


}