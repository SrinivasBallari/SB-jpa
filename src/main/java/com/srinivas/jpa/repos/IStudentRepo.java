package com.srinivas.jpa.repos;

import com.srinivas.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepo extends JpaRepository<Student,Integer> {
}
