package com.trg.springboot.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trg.springboot.DTO.AppointmentHistory;
import com.trg.springboot.DTO.BookingHistory;
import com.trg.springboot.Model1.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>
{

	
	@Query("SELECT new com.trg.springboot.DTO.AppointmentHistory(c.clientName, c.clientEmail, a.appointmentId, a.appointmentDate, a.appointmentTime, s.serviceName) " +
	           "FROM Client c " +
	           "JOIN c.appointment a " +
	           "JOIN a.service s " +
	           "WHERE c.clientRole = 'client'") 
	List<AppointmentHistory> getJoinInformationForClient();
	
	@Query("SELECT new com.trg.springboot.DTO.BookingHistory(b.bookingId, b.bookingDate, c.clientName, c.clientEmail, r.courseName) " +
	           "FROM Client c " +
	           "JOIN c.booking b " +
	           "JOIN b.course r " +
	           "WHERE c.clientRole = 'student'")
	List<BookingHistory> findAllBookingsForClient();
}
