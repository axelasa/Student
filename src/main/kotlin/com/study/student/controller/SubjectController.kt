package com.study.student.controller

import com.study.student.dto.SubjectDto
import com.study.student.global.GlobalServices.Companion.subjectService
import com.study.student.model.ApiResponse
import com.study.student.model.SubjectModel
import com.study.student.service.SubjectService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("subject")
class SubjectController(){
    @PostMapping
    fun insertSubjects(@Valid @RequestBody subjects:SubjectModel):ResponseEntity<Any>{
        var newSubject = subjectService.addSubjects(subjects)
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Subjects Saved Successfully",SubjectDto.fromSubjectEntity(newSubject)),HttpStatus.OK)
    }
}