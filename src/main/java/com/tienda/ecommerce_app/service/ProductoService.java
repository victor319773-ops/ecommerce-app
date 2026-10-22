package com.tienda.ecommerce_app.service;

import com.tienda.ecommerce_app.model.Producto;
import com.tienda.ecommerce_app.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

	
	@Autowired
	private ProductoRepository productoRepository;
	
	
	public List<Producto> ListarTodos(){
		return productoRepository.findAll();
	}
	
	
	public Producto buscarPorId(Long id) {
		return productoRepository.findById(id).orElse(null);
	}
	
	public List<Producto> listarPorCategoria(Long CategoriaId){
		return productoRepository.findByCategoriaId(CategoriaId);
	}
	
	public Producto guardar(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public void eliminar (Long id) {
		productoRepository.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
