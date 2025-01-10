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

import com.trg.springboot.Model1.Service;
import com.trg.springboot.exception.ResourceNotFoundException;
import com.trg.springboot.Repository.ServiceRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ServiceController {

 @Autowired
 private ServiceRepository serviceRepository;

public ServiceController(ServiceRepository serviceRepository)
    {
       this.serviceRepository = serviceRepository;
    }

@PostMapping("/CreateService")
    public ResponseEntity<Service> saveService(@RequestBody Service service) {
        Service savedService = serviceRepository.save(service);
        
        return new ResponseEntity<>(savedService, HttpStatus.CREATED);
    }
    
    
 @PutMapping("/UpdateService/{id}")
  public ResponseEntity<Service> updateService(@PathVariable Integer id, @RequestBody Service serviceDetails)
     {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));
        
        service.setServicePrice(serviceDetails.getServicePrice());
       
        Service updatedService = serviceRepository.save(service);
        
        return ResponseEntity.ok(updatedService);
     }

    

  @DeleteMapping("/DeleteService/{id}")
  public ResponseEntity<Void> deleteService(@PathVariable Integer id)
    {
        if (serviceRepository.existsById(id))
        {
            serviceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    

   @GetMapping("/ServicesRecord")
      public ResponseEntity<List<Service>> getAllServices() 
   {
    List<Service> services = serviceRepository.findAll();
    return new ResponseEntity<>(services, HttpStatus.OK);
   }


   @GetMapping("/DisplayService/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Integer id) 
   {
    Optional<Service> service = serviceRepository.findById(id);
    if (service.isPresent()) 
    {
        return new ResponseEntity<>(service.get(), HttpStatus.OK);
    } 
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

}


