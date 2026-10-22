package com.tienda.ecommerce_app.controller;

import com.tienda.ecommerce_app.model.Producto;
import com.tienda.ecommerce_app.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	
	@GetMapping
	public List<Producto> listar(){
		return productoService.ListarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto>buscarPorId(@PathVariable Long categoriaId){
		Producto producto = productoService.buscarPorId(categoriaId);
		if (producto == null) {
			return ResponseEntity.notFound().build();		
	   }
		return ResponseEntity.ok(producto);
	}
	
	@GetMapping("/categoria/{categoriaId}")
	public List<Producto> listarPorCategoria(@PathVariable Long categoriaId){
		return productoService.listarPorCategoria(categoriaId);
		
	}
	
	@PostMapping
	public Producto crear(@RequestBody Producto producto) {
		return productoService.guardar(producto);
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto datos){
		Producto producto =productoService.buscarPorId(id);
		if (producto == null) {
			return ResponseEntity.notFound().build();
		}
		producto.setNombre(datos.getNombre());
		producto.setDescripcion(datos.getDescripcion());
		producto.setPrecio(datos.getPrecio());
		producto.setStock(datos.getStock());
		producto.setCategoria(datos.getCategoria());
		return ResponseEntity.ok(productoService.guardar(producto));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id){
		productoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
