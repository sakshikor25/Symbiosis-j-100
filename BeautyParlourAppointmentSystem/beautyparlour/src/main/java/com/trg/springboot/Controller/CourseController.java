package com.trg.springboot.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trg.springboot.exception.ResourceNotFoundException;
import com.trg.springboot.Model1.Client;
import com.trg.springboot.Model1.Course;
import com.trg.springboot.Repository.ClientRepository;
import com.trg.springboot.Repository.CourseRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired 
    private CourseRepository courserepository;
  
	public CourseController(CourseRepository courserepository) {
		super();
		this.courserepository = courserepository;
	}
	@PostMapping("/CreateCourse")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
	    if (course.getCourseName() == null || course.getCourseName().isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // or send a custom error message
	    }
	    return new ResponseEntity<>(courserepository.save(course), HttpStatus.CREATED);
	}
    @GetMapping("/GetCourse")
    public List<Course> getAllCourse() {
        return courserepository.findAll(); 
    }
 
    @GetMapping("/GetCourseById/{id}")
    public Optional<Course> getClientByID(@PathVariable Integer id){
		return courserepository.findById(id);
    }
    
    @PutMapping("/UpdateCourse/{id}")
    public ResponseEntity<Course> updateClient(@PathVariable Integer id, @RequestBody Course courseDetails) {
    	Course course = courserepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client not exist with id :" + id, null, courseDetails));
		
		course.setCourseName(courseDetails.getCourseName());
		course.setCoursePrice(courseDetails.getCoursePrice());
		course.setDuration(courseDetails.getDuration());
	    Course updatedCourse = courserepository.save(course);
		return ResponseEntity.ok(updatedCourse);
    }
    
    @DeleteMapping("/DeleteCourse/{id}")
    public ResponseEntity<Course> deleteClient( @PathVariable Integer id){
    	  if (courserepository.existsById(id)) { 
    		  courserepository.deleteById(id); 
    	        return ResponseEntity.noContent().build(); 
    	    }
    	    return ResponseEntity.notFound().build();
    }
}
