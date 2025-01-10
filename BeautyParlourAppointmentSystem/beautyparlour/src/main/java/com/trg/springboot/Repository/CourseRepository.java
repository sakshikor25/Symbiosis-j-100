package com.trg.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trg.springboot.Model1.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course , Integer> {

}
