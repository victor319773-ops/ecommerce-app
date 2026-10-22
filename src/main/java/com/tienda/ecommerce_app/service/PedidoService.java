package com.tienda.ecommerce_app.service;

import com.tienda.ecommerce_app.model.Cliente;
import com.tienda.ecommerce_app.model.DetallePedido;
import com.tienda.ecommerce_app.model.Pedido;
import com.tienda.ecommerce_app.model.Producto;
import com.tienda.ecommerce_app.repository.ClienteRepository;
import com.tienda.ecommerce_app.repository.PedidoRepository;
import com.tienda.ecommerce_app.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> listarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    @Transactional
    public Pedido crearPedido(Pedido pedido) {

        if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            throw new IllegalArgumentException("Debes indicar el cliente del pedido");
        }
        if (pedido.getDetalles() == null || pedido.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener al menos un producto");
        }

        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("El cliente no existe"));

        pedido.setCliente(cliente);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");

        BigDecimal total = BigDecimal.ZERO;

        for (DetallePedido detalle : pedido.getDetalles()) {

            if (detalle.getProducto() == null || detalle.getProducto().getId() == null) {
                throw new IllegalArgumentException("Cada detalle necesita un producto");
            }
            if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
            }

            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Un producto del pedido no existe"));

            if (producto.getStock() < detalle.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para: " + producto.getNombre());
            }

            // Descontar stock
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            // Completar el detalle
            BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad()));
            detalle.setProducto(producto);
            detalle.setPedido(pedido);
            detalle.setPrecioUnitario(producto.getPrecio());
            
            // Método corregido
            detalle.setSubtotal(subtotal);

            total = total.add(subtotal);
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }

    public Pedido cambiarEstado(Long id, String estado) {
        Pedido pedido = buscarPorId(id);
        if (pedido == null) {
            return null;
        }
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }
}