package com.study.student.service

import com.study.student.entity.SubjectEntity
import com.study.student.model.SubjectModel
import jakarta.validation.Valid
import java.util.Optional

interface SubjectService {
    fun addSubjects(@Valid subject: SubjectModel):SubjectEntity
    //fun getSubject(subject: Set<String>):Optional <List<SubjectEntity>>
    fun getCourse(name:String):Optional<SubjectEntity>
}