package com.miniInventario.miniInventario.service;
import com.miniInventario.miniInventario.model.Inventario;
import com.miniInventario.miniInventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    private Integer generateId() {
        Integer lastId = inventarioRepository.returnId();
        return lastId + 1;
    }

    @Override
    public void saveInventario(Inventario inventario) {
        boolean validate = noRepeat(inventario);
        if (!validate) {
            inventario.setId(generateId());
            inventario.setUltimaActualizacion(LocalDateTime.now());
            inventarioRepository.addInventario(inventario);
        }
        if (validate) {
            throw new RuntimeException();
        }
    }

    @Override
    public Inventario getInventarioId(Integer id) {
        return inventarioRepository.getInventarioId(id);
    }

    @Override
    public Inventario getInventarioName(String search) {
        return inventarioRepository.getInventarioName(search);
    }

    @Override
    public List<Inventario> getAll() {
        return inventarioRepository.getAll();
    }

    @Override
    public void updateInventario(Inventario inventario) {
        if (inventario.getId() == null) {
            Inventario inv = inventarioRepository.getInventarioName(inventario.getNombre());
            inv.setCantidad(inventario.getCantidad());
            inv.setUltimaActualizacion(LocalDateTime.now());
            inventario.setPrecio(inv.getPrecio());
            inventario.setId(inv.getId());
            inventario.setUltimaActualizacion(inv.getUltimaActualizacion());
        } else {
            Inventario inv = inventarioRepository.getInventarioId(inventario.getId());
            inv.setCantidad(inventario.getCantidad());
            inv.setUltimaActualizacion(LocalDateTime.now());
            inventario.setNombre(inv.getNombre());
            inventario.setUltimaActualizacion(inv.getUltimaActualizacion());
            inventario.setPrecio(inv.getPrecio());
        }
    }

    @Override
    public List<Inventario> reverseOrder() {
        return inventarioRepository.reverseOrder();
    }

    private boolean noRepeat(Inventario inventario) {
        List<Inventario> inventarioList = inventarioRepository.getAll();
        if (inventarioList.size() == 0) {
            return false;
        }
        for (Integer i = 0; i < inventarioList.size(); i++) {
            Inventario inv = inventarioRepository.getInventarioId(i + 1);
            if (inv.getNombre().toLowerCase().equals(inventario.getNombre().toLowerCase())) {
                return true;
            }
        }
        return false;
    }


}
