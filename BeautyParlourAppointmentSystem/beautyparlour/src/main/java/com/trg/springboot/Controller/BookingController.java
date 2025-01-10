package com.trg.springboot.Controller;


import java.sql.Date;
import java.time.LocalDate;
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
import com.trg.springboot.Model1.CourseBooking;
import com.trg.springboot.Repository.BookingRepository;
import com.trg.springboot.Repository.ClientRepository;
import com.trg.springboot.Repository.CourseRepository;


@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private CourseRepository courseRepository;

    public BookingController(BookingRepository bookingRepository, ClientRepository clientRepository, CourseRepository courseRepository) {
        super();
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/CreateBooking")
    public ResponseEntity<CourseBooking> saveBooking(@RequestBody CourseBooking booking) {
        if (booking.getClient() != null && booking.getCourse() != null) {
            
            Optional<Client> clientOpt = clientRepository.findById(booking.getClient().getClientId());
            Optional<Course> courseOpt = courseRepository.findById(booking.getCourse().getCourseId());
            
            if (clientOpt.isPresent() && courseOpt.isPresent()) {
                booking.setClient(clientOpt.get());
                booking.setCourse(courseOpt.get());
                
                LocalDate currentDate = LocalDate.now();
                Date sqlDate = Date.valueOf(currentDate);
                booking.setBookingDate(sqlDate);
                
                CourseBooking savedBooking = bookingRepository.save(booking);
                System.out.println("Saved Booking: " + savedBooking); 
                return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
         
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GetAllBooking")
    public List<CourseBooking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/GetBookingById/{id}")
    public Optional<CourseBooking> getBookingById(@PathVariable Integer id) {
        return bookingRepository.findById(id);
    }
    
    @PutMapping("/UpdateBooking/{id}")
    public ResponseEntity<CourseBooking> updateBooking(Integer bookingId, CourseBooking status) {
        Optional<CourseBooking> bookingOpt = bookingRepository.findById(bookingId);
        
        if (bookingOpt.isPresent()) {
            CourseBooking booking = bookingOpt.get();
            
            Date existingBookingDate = booking.getBookingDate();
            booking.setBookingStatus(status.getBookingStatus());
            booking.setBookingDate(existingBookingDate);

            CourseBooking updatedBooking = bookingRepository.save(booking);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/DeleteBooking/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
