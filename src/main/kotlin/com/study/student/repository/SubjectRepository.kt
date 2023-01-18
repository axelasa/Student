package com.study.student.repository

import com.study.student.entity.SubjectEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface SubjectRepository:JpaRepository<SubjectEntity,Long>{
    fun findByCourseName(name:String):Optional<SubjectEntity>
}