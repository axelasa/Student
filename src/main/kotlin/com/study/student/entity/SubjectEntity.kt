package com.study.student.entity

import com.study.student.model.SubjectModel
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.springframework.data.annotation.Immutable

@Entity
@Table(name = "subject")
data  class SubjectEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id:Long?,
    @Column(name = "course",unique = true)
    val courseName:String,
    @ElementCollection
    @Column(name = "subjects",unique = true)
    val subjects:Set<String> = setOf(),
    @OneToMany(fetch = FetchType.LAZY)
    val student:MutableSet<StudentEntity>

        ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as SubjectEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $courseName , subjects = $subjects , student = $student )"
    }
    companion object{
        fun newSubjects(subjects: SubjectModel):SubjectEntity{
            val newSub = SubjectEntity(null,subjects.courseName, subjects.subject, mutableSetOf())
            return newSub
        }
    }
}