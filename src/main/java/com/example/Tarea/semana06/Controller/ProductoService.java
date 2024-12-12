/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Tarea.semana06.Controller;

import com.example.Tarea.semana06.Model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
class ProductoService {
     private List<Producto> productos = new ArrayList<>();
    
   
    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productos;
    }

    // Obtener un producto por ID
    public Optional<Producto> obtenerPorId(Long id) {
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    // Crear un nuevo producto
    public Producto crearProducto(Producto producto) {
        long newId = productos.size() + 1;
        producto.setId(newId);
        productos.add(producto);
        return producto;
    }

    // Actualizar un producto existente
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto producto = obtenerPorId(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(productoActualizado.getNombre());
        producto.setCategoria(productoActualizado.getCategoria());
        producto.setFechaVencimiento(productoActualizado.getFechaVencimiento());
        producto.setStockMinimo(productoActualizado.getStockMinimo());
        producto.setStockMaximo(productoActualizado.getStockMaximo());
        producto.setMarca(productoActualizado.getMarca());
        return producto;
    }

    // Eliminar un producto por ID
    public boolean eliminarProducto(Long id) {
        return productos.removeIf(producto -> producto.getId().equals(id));
    }
}
