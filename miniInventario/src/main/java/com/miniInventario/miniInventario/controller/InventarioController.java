package com.miniInventario.miniInventario.controller;

import com.miniInventario.miniInventario.model.Inventario;
import com.miniInventario.miniInventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/")
    public String greetings() {
        return "Bienvenidos a mi API";
    }

    @PostMapping("/api/inventario/add")
    public Inventario inventario(@RequestBody Inventario inventario) {
        inventarioService.saveInventario(inventario);
        return inventario;
    }

    @GetMapping("/api/inventario/getbyid/{id}")
    public Inventario inventario(@PathVariable("id") Integer id) {
        return inventarioService.getInventarioId(id);
    }

    @GetMapping("/api/inventario/all")
    public List<Inventario> getAll() {
        return inventarioService.getAll();
    }

    @GetMapping("/api/inventario/getbyname/{name}")
    public Inventario inventario(@PathVariable("name") String name) {
        return inventarioService.getInventarioName(name);
    }

    @PutMapping("/api/inventario/update")
    public Inventario updateInventario(@RequestBody Inventario inventario){
        inventario.setPrecio(0);
        inventarioService.updateInventario(inventario);
        return inventario;
    }

    @GetMapping("/api/inventario/reverse")
    public List<Inventario> reverseOrder(){
        return inventarioService.reverseOrder();
    }
}
