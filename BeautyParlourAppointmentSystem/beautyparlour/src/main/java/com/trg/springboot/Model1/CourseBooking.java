package com.trg.springboot.Model1;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="Booking2")
public class CourseBooking {
 
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int bookingId;
private Date bookingDate;
private String bookingStatus; 

@ManyToOne
@JoinColumn(name="client_id")
private Client client;

@ManyToOne 
@JoinColumn(name="course_id")
private Course course;

public int getBookingId() {
	return bookingId;
}

public void setBookingId(int bookingId) {
	this.bookingId = bookingId;
}

public Date getBookingDate() {
	return bookingDate;
}

public void setBookingDate(Date bookingDate) {
	this.bookingDate = bookingDate;
}

public String getBookingStatus() {
	return bookingStatus;
}

public void setBookingStatus(String bookingStatus) {
	this.bookingStatus = bookingStatus;
}

public Client getClient() {
	return client;
}

public void setClient(Client client) {
	this.client = client;
}

public Course getCourse() {
	return course;
}

public void setCourse(Course course) {
	this.course = course;
}

@Override
public String toString() {
	return "CourseBooking [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", bookingStatus=" + bookingStatus
			+ ", client=" + client + ", course=" + course + "]";
}

@PreUpdate
public void preUpdate() {
    // If bookingDate is null, set it to the current date (optional)
    if (this.bookingDate == null) {
        this.bookingDate = new Date(System.currentTimeMillis()); 
    }
}


}