package com.tienda_k.api.controller;

import java.util.List;

// estos son los imports para las anotaciones y clases de Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//me comunico con el modelo y el servicio
import com.tienda_k.api.model.product;
import com.tienda_k.api.service.productService;

@RestController
@RequestMapping("/api/productos")

public class productController {
    
    @Autowired
    private productService productService;

    @GetMapping
    public List<product> listar() {
        return productService.listarTodos();
    }

    @PostMapping
    public product crear(@RequestBody product producto) {
        return productService.guardar(producto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<product> obtenerPorId(@PathVariable Long id) {
        return productService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productService.eliminar(id);
    }

    @PutMapping("/{id}")
public ResponseEntity<product> actualizarProducto(@PathVariable Long id, @RequestBody product detallesProducto) {
    try {
        product productoActualizado = productService.update(id, detallesProducto);
        return ResponseEntity.ok(productoActualizado);
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}
}
