package com.trg.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trg.springboot.Model1.CourseBooking;

@Repository
public interface BookingRepository extends JpaRepository<CourseBooking , Integer> {

}
