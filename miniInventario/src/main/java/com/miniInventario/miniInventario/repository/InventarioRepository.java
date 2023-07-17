package com.miniInventario.miniInventario.repository;

import com.miniInventario.miniInventario.model.Inventario;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InventarioRepository {
    private Map<Integer, Inventario> inventarioMap = new HashMap<>();

    public void addInventario(Inventario inventario) {
        inventarioMap.put(inventario.getId(), inventario);
    }

    public Inventario getInventarioId(Integer id) {
        return inventarioMap.get(id);
    }

    public List<Inventario> getAll() {
        return new ArrayList<>(inventarioMap.values());
    }

    public Integer returnId() {
        Integer lastId = 0;
        for (Integer i : inventarioMap.keySet()) {
            lastId = i;
        }
        return lastId;
    }

    public Inventario getInventarioName(String search) {
        for (Integer i = 0; i < inventarioMap.size(); i++) {
            Inventario inv = inventarioMap.get(i + 1);
            System.out.println(inv.getNombre());
            if (inv.getNombre().toLowerCase().contains(search.toLowerCase())) {
                return inv;
            }
        }
        System.out.println("No se encontró el valor.");
        throw new RuntimeException("No se encontró el valor");
    }

    public Inventario updateInventario(Inventario inventario){
        return inventarioMap.put(inventario.getId(), inventario);
    }

    public List<Inventario> reverseOrder(){
        List<Inventario> inventarioList = getAll();
        Comparator<Inventario> comparador = new Comparator<Inventario>() {
            @Override
            public int compare(Inventario i1, Inventario i2) {
                return 0;
            }
        };
        Collections.sort(inventarioList, comparador);
        Collections.reverse(inventarioList);
        return inventarioList;
    }
}


