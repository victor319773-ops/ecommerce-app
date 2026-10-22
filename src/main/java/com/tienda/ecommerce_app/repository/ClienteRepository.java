package com.tienda.ecommerce_app.repository;

import com.tienda.ecommerce_app.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	
	

}
