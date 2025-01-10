package com.trg.springboot.Controller;

import java.util.ArrayList;
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

import com.trg.springboot.DTO.AppointmentHistory;
import com.trg.springboot.DTO.BookingHistory;
import com.trg.springboot.Model1.Client;
import com.trg.springboot.exception.ResourceNotFoundException;
import com.trg.springboot.Repository.ClientRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

	public ClientController(ClientRepository clientRepository) {
      super();
        this.clientRepository = clientRepository;
      
    }
	    @GetMapping("/getInfo")
	    public ResponseEntity<List<AppointmentHistory>> getJoinInformation() 
	    {
	        
	        List<AppointmentHistory> appointmentHistories = clientRepository.getJoinInformationForClient();
	        System.out.println(appointmentHistories );
	        if (appointmentHistories.isEmpty())
               {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	           }
	        return new ResponseEntity<>(appointmentHistories, HttpStatus.OK);
	    }
	 @GetMapping("/getdata")
	 public ResponseEntity<List<BookingHistory>> findAllBookings()
	 {
	        List<BookingHistory> bookinghistories = clientRepository.findAllBookingsForClient();
	        System.out.println( bookinghistories);
	        if (bookinghistories.isEmpty())
	        {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	        }
	        return new ResponseEntity<>(bookinghistories, HttpStatus.OK);
	  }
      @GetMapping("/ClientsRecord")
        public ResponseEntity<List<Client>> getAllClients() 
      {
        List<Client> clients = clientRepository.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
      }
    
    
    @GetMapping("/DisplayClient/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) 
    {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) 
        {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/CreateClient")
    public ResponseEntity<Client> saveClient(@RequestBody Client client)
    {
       return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
    }
    @DeleteMapping("/DeleteClient/{id}")
    public ResponseEntity<Client> deleteClient( @PathVariable Integer id)
    {
    	  if (clientRepository.existsById(id)) 
    	  { 
    	        clientRepository.deleteById(id); 
    	        return ResponseEntity.noContent().build(); 
    	  }
    	    return ResponseEntity.notFound().build();
    }
    
    
    @PutMapping("/UpdateClient/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client clientDetails)
    {
    	Client client = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client not exist with id :" + id, null, clientDetails));
		
		client.setClientName(clientDetails.getClientName());
		client.setClientMobileno(clientDetails.getClientMobileno());
		client.setClientEmail(clientDetails.getClientEmail());
		Client updatedClient = clientRepository.save(client);
		return ResponseEntity.ok(updatedClient);
    }
}

