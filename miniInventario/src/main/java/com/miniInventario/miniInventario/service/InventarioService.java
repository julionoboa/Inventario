package com.miniInventario.miniInventario.service;

import com.miniInventario.miniInventario.model.Inventario;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InventarioService {
    void saveInventario(Inventario inventario);
    Inventario getInventarioId(Integer id);
    Inventario getInventarioName(String search);
    List<Inventario> getAll();
    Inventario updateInventario(Inventario inventario);
    List<Inventario> reverseOrder();
}
