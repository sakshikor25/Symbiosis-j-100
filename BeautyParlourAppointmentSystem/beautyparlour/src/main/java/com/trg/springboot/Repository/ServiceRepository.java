package com.trg.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.trg.springboot.Model1.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer>
{

}

