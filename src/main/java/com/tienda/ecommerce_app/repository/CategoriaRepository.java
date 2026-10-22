package com.tienda.ecommerce_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tienda.ecommerce_app.model.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
