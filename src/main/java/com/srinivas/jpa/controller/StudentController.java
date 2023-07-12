package com.srinivas.jpa.controller;

import com.srinivas.jpa.entity.Student;
import com.srinivas.jpa.repos.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    // the autowired annotation is used to create objects for the created reference variable's.
    @Autowired
    IStudentRepo studRepo;
    @PostMapping("/api/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return  new ResponseEntity<>(studRepo.save(student), HttpStatus.CREATED);
    }

    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(studRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        Optional<Student> requiredStudent = studRepo.findById(id);
        if(requiredStudent.isPresent()){
            return new ResponseEntity<>(requiredStudent.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/students/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable int id,@RequestBody Student updatedStudDetails){
        Optional<Student> requiredStudent = studRepo.findById(id);
        if(requiredStudent.isPresent()){
            requiredStudent.get().setStudentName(updatedStudDetails.getStudentName());
            requiredStudent.get().setStudentAddress(updatedStudDetails.getStudentAddress());
            requiredStudent.get().setStudentEmail(updatedStudDetails.getStudentEmail());
            return new ResponseEntity<>(studRepo.save(requiredStudent.get()),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id){
        Optional<Student> studentToBeDeleted = studRepo.findById(id);
        if(studentToBeDeleted.isPresent()){
            studRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
