package com.damb.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.damb.springboot.backend.apirest.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

}
