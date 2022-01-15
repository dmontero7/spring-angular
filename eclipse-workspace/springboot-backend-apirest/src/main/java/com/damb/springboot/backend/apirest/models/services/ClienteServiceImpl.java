package com.damb.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damb.springboot.backend.apirest.models.dao.IClienteDao;
import com.damb.springboot.backend.apirest.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{
	@Autowired
	private IClienteDao dao;
	
	@Transactional(readOnly = true)//se puede omitir porque todas las clases de JPARepository son transaccionales
	public List<Cliente> findAll() {
		return dao.findAll();
	}

}
