package com.corporativoX.EjercicioApiRest.controllers;

import com.corporativoX.EjercicioApiRest.entities.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Laura Gómez", "laura.gomez@example.com", 20, "Matemáticas"),
            new Student(2, "Carlos Ruiz", "carlos.ruiz@example.com", 22, "Ingeniería Informática"),
            new Student(3, "Marta López", "marta.lopez@example.com", 19, "Biología"),
            new Student(4, "Andrés Pérez", "andres.perez@example.com", 21, "Física")
    ));

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>>  getStudents() {
        return ResponseEntity.ok(students);
    }

    @RequestMapping(value = "/{email}",method = RequestMethod.GET)
    public ResponseEntity<?> getStudentByEmail(@PathVariable  String email) {
        for(Student student : students) {
            if(student.getEmail().equals(email)) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with email: " + email + ", not found");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postStudent(@RequestBody Student student){
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student succesfully created: " + student.getName());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> putStudent(@RequestBody Student student){
        for(Student s : students){
            if(s.getID() == student.getID()){
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                s.setAge(student.getAge());
                s.setCourse(student.getCourse());
                return ResponseEntity.ok("Student succesfully modified: " + student.getName());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID: " + student.getID() + ", not found");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable int id){
        for(Student s : students){
            if(s.getID() == id){
                students.remove(s);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student succesfully deleted: " + s.getName());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID: " + id + ", not found");
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> patchStudent(@RequestBody Student student){
        for(Student s: students){
            if(s.getID() == student.getID()){

                if(student.getName() != null){
                    s.setName(student.getName());
                }
                if(student.getEmail() != null){
                    s.setEmail(student.getEmail());
                }
                if(student.getAge() != 0){
                    s.setAge(student.getAge());
                }
                if(student.getCourse() != null){
                    s.setCourse(student.getCourse());
                }
                return ResponseEntity.ok("Student succesfully modified: " + s.getName());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID: " + student.getID() + ", not found");
    }

}