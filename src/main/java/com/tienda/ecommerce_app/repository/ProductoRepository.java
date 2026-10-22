package com.tienda.ecommerce_app.repository;

import com.tienda.ecommerce_app.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	List<Producto> findByCategoriaId(Long categoriaId);

}
