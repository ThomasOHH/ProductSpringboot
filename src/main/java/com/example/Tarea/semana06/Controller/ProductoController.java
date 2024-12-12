/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Tarea.semana06.Controller;

import com.example.Tarea.semana06.Model.Producto;
import com.example.Tarea.semana06.Repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/")
public class ProductoController {
      @Autowired
    private ProductoRepository productoRepository;

      @Autowired
    private ProductoService productoService;

    // Mostrar todos los productos
    @GetMapping
    public String mostrarProductos(Model model) {
        List<Producto> productos = productoService.obtenerTodos();
        model.addAttribute("productos", productos);
        return "producto.index";  // Retorna la vista que muestra los productos
    }

    // Mostrar un formulario para agregar un producto
    @GetMapping("/nuevo")
    public String mostrarFormularioProducto() {
        return "producto.form";  // Vista para agregar producto
    }

    // Crear un nuevo producto
    @PostMapping
    public String crearProducto(@ModelAttribute Producto producto) {
        productoService.crearProducto(producto);
        return "redirect:/productos";  // Redirige a la lista de productos
    }

    // Mostrar un formulario para editar un producto
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Optional<Producto> producto = productoService.obtenerPorId(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "producto.form";  // Vista para editar el producto
        }
        return "redirect:/productos";  // Si no se encuentra el producto, redirige a la lista
    }

    // Actualizar un producto
    @PostMapping("/editar/{id}")
    public String actualizarProducto(@PathVariable("id") Long id, @ModelAttribute Producto producto) {
        productoService.actualizarProducto(id, producto);
        return "redirect:/productos";  // Redirige a la lista de productos
    }

    // Eliminar un producto
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        if (productoService.eliminarProducto(id)) {
            return "redirect:/productos";  // Redirige a la lista de productos
        }
        return "error";  // Si no se pudo eliminar, muestra una página de error
    }

}
