package com.corporativoX.EjercicioApiRest.controllers;

import com.corporativoX.EjercicioApiRest.entities.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public List<Student> getStudents() {
        return students;
    }

    @RequestMapping(value = "/{email}",method = RequestMethod.GET)
    public Student getStudentByEmail(@PathVariable  String email) {
        for(Student student : students) {
            if(Objects.equals(student.getEmail(), email)) {
                return student;
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Student postStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Student putStudent(@RequestBody Student student){
        for(Student s : students){
            if(s.getID() == student.getID()){
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                s.setAge(student.getAge());
                s.setCourse(student.getCourse());
                return s;
            }
        }
        return null;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Student deleteStudent(@PathVariable int id){
        for(Student s : students){
            if(s.getID() == id){
                students.remove(s);
                return s;
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public Student patchStudent(@RequestBody Student student){
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
                return s;
            }
        }
        return null;
    }

}