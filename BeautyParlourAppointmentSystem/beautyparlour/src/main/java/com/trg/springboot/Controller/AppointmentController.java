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
import com.trg.springboot.Model1.Appointment;
import com.trg.springboot.Model1.Client;
import com.trg.springboot.Model1.Service;
import com.trg.springboot.exception.ResourceNotFoundException;
import com.trg.springboot.Repository.AppointmentRepository;
import com.trg.springboot.Repository.ClientRepository;
import com.trg.springboot.Repository.ServiceRepository;

@RestController
@RequestMapping("/api")

public class AppointmentController {
	

	    @Autowired
	    private AppointmentRepository appointmentRepository;
	    @Autowired
	    private ClientRepository clientRepository; 
	    @Autowired
	    private ServiceRepository serviceRepository; 
	    
	    public AppointmentController(AppointmentRepository appointmentRepository, ClientRepository clientRepository, ServiceRepository serviceRepository) {
	        super();
	        this.appointmentRepository = appointmentRepository;
	        this.clientRepository = clientRepository;
	        this.serviceRepository = serviceRepository;
	    }
	    
	    @GetMapping("/AppointmentsRecord")
	    public ResponseEntity<List<Appointment>> getAllClient() {
	        List<Appointment> appointments = appointmentRepository.findAll();
	        return new ResponseEntity<>(appointments, HttpStatus.OK);
	    }
	    
	    
	    @GetMapping("/DisplayAppointment/{id}")
	    public ResponseEntity<Appointment> getClientById(@PathVariable Integer id) {
	        Optional<Appointment>app = appointmentRepository.findById(id);
	        if (app.isPresent()) {
	            return new ResponseEntity<>(app.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    
	    @PostMapping("/CreateAppointment")
	    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
	        if (appointment.getClient() != null && appointment.getService() != null) {
	           
	            Optional<Client> clientOpt = clientRepository.findById(appointment.getClient().getClientId());
	            Optional<Service> serviceOpt = serviceRepository.findById(appointment.getService().getServiceId());
	            
	            if (clientOpt.isPresent() && serviceOpt.isPresent()) {
	                appointment.setClient(clientOpt.get());
	                appointment.setService(serviceOpt.get());
	                Appointment savedAppointment = appointmentRepository.save(appointment);
	                return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
	            } 
	                else {
	                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	            }
	        } else {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
	   
	    @PutMapping("/UpdateAppointment/{id}")
	    public ResponseEntity<Appointment> updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointmentDetails) {
	   
	        Appointment existingAppointment = appointmentRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + id));

	        
	        Client client = clientRepository.findById(appointmentDetails.getClient().getClientId())
	                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + appointmentDetails.getClient().getClientId()));
	        
	        Service service = serviceRepository.findById(appointmentDetails.getService().getServiceId())
	                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + appointmentDetails.getService().getServiceId()));
	        existingAppointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
	        existingAppointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
	        existingAppointment.setAppointmentStatus(appointmentDetails.getAppointmentStatus());
	        existingAppointment.setClient(client); 
	        existingAppointment.setService(service);  
	        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
	        return ResponseEntity.ok(updatedAppointment);
	    }
	    
	    
	    
	    @DeleteMapping("/DeleteAppointment/{id}")
	    public ResponseEntity<Appointment> deleteAppointment( @PathVariable Integer id){
	    	  if (appointmentRepository.existsById(id)) { 
	    	        appointmentRepository.deleteById(id); 
	    	        return ResponseEntity.noContent().build(); 
	    	    }
	    	    return ResponseEntity.notFound().build();
	    }
}

