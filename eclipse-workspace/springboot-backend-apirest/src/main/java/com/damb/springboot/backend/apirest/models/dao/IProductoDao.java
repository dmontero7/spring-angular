package com.damb.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.damb.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {
    @Query("from Producto p where p.nombre like %?1%")
    public List<Producto> findByNombre(String termino);

    public List<Producto> findByNombreContainingIgnoreCase(String termino);

    public List<Producto> findByNombreStartingWithIgnoreCase(String termino);

}
