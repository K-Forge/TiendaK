package com.tienda_k.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_k.api.model.product;
import com.tienda_k.api.repository.productRepository;

@Service
public class productService {
    @Autowired
    private productRepository productRepository;

    public List<product> listarTodos() {
        return productRepository.findAll();
    }

    public product guardar(product producto) {
        return productRepository.save(producto);
    }

    public Optional<product> buscarPorId(Long id) {
        return productRepository.findById(id);
    }

    public void eliminar(Long id) {
        productRepository.deleteById(id);
    }

    public product update(Long id, product productoDetalles) {
    // 1. Buscamos el producto existente
    
    return productRepository.findById(id).map(producto -> {
        // 2. Seteamos los nuevos valores que vienen del objeto 'productoDetalles'
        producto.setName(productoDetalles.getName());
        producto.setDescription(productoDetalles.getDescription());
        producto.setPrice(productoDetalles.getPrice());
        
        // 3. Guardamos los cambios
        return productRepository.save(producto);
    }).orElseThrow(() -> new RuntimeException("Producto no encontrado con el id: "+id));
}
}