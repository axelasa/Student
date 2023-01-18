package com.study.student.controller

import com.study.student.entity.StudentEntity
import com.study.student.model.ApiResponse
import com.study.student.model.StudentModel
import com.study.student.model.UpdatedStudent
import com.study.student.service.StudentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("Student")
class StudentController(var studentService: StudentService) {
    @GetMapping
    fun getStudent(@RequestParam("username", required = true) username:String):ResponseEntity<Any>{
        val optionalStudent = studentService.getStudent(username)
        if (optionalStudent.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This Student Does Not Exist",null),HttpStatus.NOT_FOUND)

        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Student found",optionalStudent.get()),HttpStatus.OK)
    } @GetMapping("id")
    fun getStudentById(@RequestParam("user_id", required = true) id:Long):ResponseEntity<Any>{
        val optionalStudent = studentService.getStudentsById(id)
        if (optionalStudent.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This Student Does Not Exist",null),HttpStatus.NOT_FOUND)

        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Student found",optionalStudent.get()),HttpStatus.OK)
    }
    @GetMapping("unique_student")
    fun getStudentByUsernameAndId(@RequestParam("username", required = true)username:String ,@RequestParam("id", required = true)id:Long):ResponseEntity<Any>{
        val optionalStudent = studentService.getStudentByUniqueValues (username,id)
        if (optionalStudent.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This Student Does Not Exist",null),HttpStatus.NOT_FOUND)

        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Student found",optionalStudent.get()),HttpStatus.OK)
    }
    @GetMapping("all")
    fun getAllStudents(): List<StudentEntity> = studentService.getAllStudents()

    @PostMapping
    fun createStudent(@Valid @RequestBody studentModel:StudentModel):ResponseEntity<Any>{
        val newStudent = studentService.createStudent(studentModel)

        return ResponseEntity(ApiResponse(HttpStatus.CREATED.value(),"Student created",newStudent),HttpStatus.CREATED)
    }
    @PutMapping
    fun updateStudent(@Valid @RequestBody studentModel: UpdatedStudent):ResponseEntity<Any>{
        val updatedStudent = studentService.updateStudent(studentModel)

        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Student data updated successfully",updatedStudent),HttpStatus.OK)
    }
}