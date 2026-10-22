package com.tienda.ecommerce_app.controller;

import com.tienda.ecommerce_app.model.Cliente;
import com.tienda.ecommerce_app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/clientes")
public class ClienteController {
	
	 @Autowired
	    private ClienteService clienteService;

	    @GetMapping
	    public List<Cliente> listar() {
	        return clienteService.listarTodos();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
	        Cliente cliente = clienteService.buscarPorId(id);
	        if (cliente == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(cliente);
	    }

	    @PostMapping
	    public Cliente crear(@RequestBody Cliente cliente) {
	        return clienteService.guardar(cliente);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente datos) {
	        Cliente cliente = clienteService.buscarPorId(id);
	        if (cliente == null) {
	            return ResponseEntity.notFound().build();
	        }
	        cliente.setNombre(datos.getNombre());
	        cliente.setEmail(datos.getEmail());
	        cliente.setTelefono(datos.getTelefono());
	        cliente.setDireccion(datos.getDireccion());
	        return ResponseEntity.ok(clienteService.guardar(cliente));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
	        clienteService.eliminar(id);
	        return ResponseEntity.noContent().build();
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
