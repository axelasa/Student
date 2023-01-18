package com.study.student.entity

import com.study.student.model.StudentModel
import com.study.student.model.SubjectModel
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.Hibernate
import java.util.*

@Entity
@Table(name ="student")
data class StudentEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    var id:Long?,
    @Column(name = "crated_at")
    var createdAt:Date,
    @field:NotEmpty
    @Column(name = "username", nullable = false, unique = true)
    var userName:String,
    @Column(name = "firstname")
    var firstname:String,
    @Column(name = "lastname")
    var lastName: String,
    @field:NotNull
    @Column(name = "age", nullable = false)
    var age:Date,
    //@JoinColumn(name = "subject")
    @ManyToOne(cascade = [CascadeType.ALL])
    var subject:SubjectEntity?,
    @Column(name = "updated_at")
    var updatedAt:Date
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as StudentEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , createdAt = $createdAt , userName = $userName , firstname = $firstname , lastName = $lastName , age = $age , subject = $subject , updatedAt = $updatedAt )"
    }
    companion object{
        fun newStudent(student:StudentModel):StudentEntity{
            val now = Date()
            val pupil = StudentEntity(null, createdAt =now ,student.userName,student.firstname,student.lastname,student.age, updatedAt = now,
                subject = null)
            return pupil
        }
    }
}

