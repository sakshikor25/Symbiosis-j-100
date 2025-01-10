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

import com.trg.springboot.Model1.Admin;
import com.trg.springboot.Model1.Client;
import com.trg.springboot.Repository.AdminRepository;
import com.trg.springboot.Repository.ClientRepository;
import com.trg.springboot.exception.ResourceNotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AdminController {
	@Autowired
    private AdminRepository AdminRepository;
	

    public AdminController(AdminRepository AdminRepository) {
        super();
        this.AdminRepository = AdminRepository;
    }
    
    
    
    @GetMapping("/AdminRecord")
    public ResponseEntity<List<Admin>> getAllAdmin() {
        List<Admin> Admin = AdminRepository.findAll();
        return new ResponseEntity<>(Admin, HttpStatus.OK);
    }
    
    
    @GetMapping("/DisplayAdmin/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer id) {
        Optional<Admin> Admin = AdminRepository.findById(id);
        if (Admin.isPresent()) {
            return new ResponseEntity<>(Admin.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    
    @PostMapping("/CreateAdmin")
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin Admin) {
       return new ResponseEntity<>(AdminRepository.save(Admin), HttpStatus.CREATED);
    }
    
    
    
    @DeleteMapping("/DeleteAdmin/{id}")
    public ResponseEntity<Admin> deleteAdmin( @PathVariable Integer id){
    	  if (AdminRepository.existsById(id)) { 
    		  AdminRepository.deleteById(id); 
    	        return ResponseEntity.noContent().build(); 
    	    }
    	    return ResponseEntity.notFound().build();
    }
    
    
    @PutMapping("/UpdateAdmin/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer id, @RequestBody Admin AdminDetails) {
    	Admin admin = AdminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not exist with id :" + id, null, AdminDetails));
		
    	admin.setAdminName(AdminDetails.getAdminName());
    	
    	admin.setAdminEmail(AdminDetails.getAdminEmail());
    	Admin updatedAdmin = AdminRepository.save(admin);
		return ResponseEntity.ok(updatedAdmin);
    }
}
