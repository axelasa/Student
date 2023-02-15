package com.study.student.controller

import com.study.student.dto.StudentDto
import com.study.student.entity.StudentEntity
import com.study.student.global.GlobalServices.Companion.studentService
import com.study.student.model.ApiResponse
import com.study.student.model.EnrollForm
import com.study.student.model.StudentModel
import com.study.student.model.UpdatedStudent
import com.study.student.service.StudentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("Student")
class StudentController() {
    @GetMapping
    fun getStudent(@RequestParam("username", required = true) username:String):ResponseEntity<Any>{
        val optionalStudent = studentService.getStudent(username)
        if (optionalStudent.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This Student Does Not Exist",null),HttpStatus.NOT_FOUND)

        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Student found",StudentDto.fromStudentEntity(optionalStudent.get())),HttpStatus.OK)
    } @GetMapping("id")
    fun getStudentById(@RequestParam("user_id", required = true) id:Long):ResponseEntity<Any>{
        val optionalStudent = studentService.getStudentsById(id)
        if (optionalStudent.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This Student Does Not Exist",null),HttpStatus.NOT_FOUND)

        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Student found",StudentDto.fromStudentEntity(optionalStudent.get())),HttpStatus.OK)
    }
    @GetMapping("unique_student")
    fun getStudentByUsernameAndId(@RequestParam("username", required = true)username:String ,@RequestParam("id", required = true)id:Long):ResponseEntity<Any>{
        val optionalStudent = studentService.getStudentByUniqueValues (username,id)
        if (optionalStudent.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This Student Does Not Exist",null),HttpStatus.NOT_FOUND)

        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Student found",StudentDto.fromStudentEntity(optionalStudent.get())),HttpStatus.OK)
    }
    @GetMapping("all")
    fun getAllStudents(): List<StudentDto> = studentService.getAllStudents().stream().map {
        StudentDto.fromStudentEntity(it)
    }.toList()

    @PostMapping("new_student")
    fun createStudent(@Valid @RequestBody studentModel:StudentModel):ResponseEntity<Any>{
        val newStudent = studentService.createStudent(studentModel)

        return ResponseEntity(ApiResponse(HttpStatus.CREATED.value(),"Student created",StudentDto.fromStudentEntity(newStudent)),HttpStatus.CREATED)
    }
    @PutMapping("update_student")
    fun updateStudent(@Valid @RequestBody studentModel: UpdatedStudent):ResponseEntity<Any>{
        val updatedStudent = studentService.updateStudent(studentModel)

        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Student data updated successfully",StudentDto.fromStudentEntity(updatedStudent)),HttpStatus.OK)
    }
    @PutMapping("enroll")
    fun enrollStudent(@RequestBody enrollForm: EnrollForm):ResponseEntity<Any>{
       studentService.enrollCourse(enrollForm)

        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Success",null),HttpStatus.OK)
    }
}