package com.tienda.ecommerce_app.controller;

import com.tienda.ecommerce_app.model.Categoria;
import com.tienda.ecommerce_app.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id){
		Categoria categoria = categoriaService.buscarPorId(id);
		if (categoria == null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(categoria);
	}
	
	
	@PostMapping
	public Categoria crear(@RequestBody Categoria categoria) {
		return categoriaService.guardar(categoria);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria datos){
		Categoria categoria = categoriaService.buscarPorId(id);
		if (categoria == null) {
			return ResponseEntity.notFound().build();
		}
		categoria.setNombre(datos.getNombre());
		categoria.setDescripcion(datos.getDescripcion());
		return ResponseEntity.ok(categoriaService.guardar(categoria));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id){
		categoriaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
