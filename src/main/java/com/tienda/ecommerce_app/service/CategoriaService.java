package com.tienda.ecommerce_app.service;

import com.tienda.ecommerce_app.model.Categoria;
import com.tienda.ecommerce_app.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public List <Categoria> listarTodas() {
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarPorId(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	
	public Categoria guardar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	
	
	public void eliminar (Long id) {
		categoriaRepository.deleteById(id);
	}
	
		
	

}
