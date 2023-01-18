package com.study.student.repository

import com.study.student.entity.SubjectEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface SubjectRepository:JpaRepository<SubjectEntity,Long>{
    fun findBySubjects(subject:Set<String>):Optional<List<SubjectEntity>>
    fun findByCourseName(name:String):Optional<SubjectEntity>
}