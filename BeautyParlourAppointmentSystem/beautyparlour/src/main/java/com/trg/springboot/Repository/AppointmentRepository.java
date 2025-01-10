package com.trg.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trg.springboot.Model1.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer>
{

}

