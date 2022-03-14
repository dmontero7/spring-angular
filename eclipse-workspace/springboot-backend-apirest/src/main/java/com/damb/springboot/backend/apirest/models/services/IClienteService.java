package com.damb.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.damb.springboot.backend.apirest.exceptions.ClienteException;
import com.damb.springboot.backend.apirest.models.entity.Cliente;
import com.damb.springboot.backend.apirest.models.entity.Factura;
import com.damb.springboot.backend.apirest.models.entity.Producto;
import com.damb.springboot.backend.apirest.models.entity.Region;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public Cliente findById(Long id);

	public Page<Cliente> findAll(Pageable pageable);

	public Cliente upload(MultipartFile archivo, Long id) throws ClienteException;

	public List<Region> findAllRegiones();

	public Factura findFacturaById(Long id);

	public Factura saveFactura(Factura factura);

	public void deleteFacturaById(Long id);

	public List<Producto> findProductoByNombre(String nombre);
}
