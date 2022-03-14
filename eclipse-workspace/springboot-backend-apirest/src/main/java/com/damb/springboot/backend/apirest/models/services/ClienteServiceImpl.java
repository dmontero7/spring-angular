package com.damb.springboot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.damb.springboot.backend.apirest.exceptions.ClienteException;
import com.damb.springboot.backend.apirest.models.dao.IClienteDao;
import com.damb.springboot.backend.apirest.models.dao.IFacturaDao;
import com.damb.springboot.backend.apirest.models.dao.IProductoDao;
import com.damb.springboot.backend.apirest.models.entity.Cliente;
import com.damb.springboot.backend.apirest.models.entity.Factura;
import com.damb.springboot.backend.apirest.models.entity.Producto;
import com.damb.springboot.backend.apirest.models.entity.Region;

@Service
public class ClienteServiceImpl implements IClienteService {
	@Autowired
	private IClienteDao dao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired
	private IProductoDao productoDao;

	private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	@Override
	@Transactional(readOnly = true)
	// se puede omitir porque todas las clases de JPARepository son transaccionales
	public List<Cliente> findAll() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return dao.save(cliente);
	}

	@Override
	public void delete(Long id) {
		try {
			deleteImg(findById(id));
		} catch (ClienteException e) {
		}
		dao.deleteById(id);
	}

	@Override
	public Cliente findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	public Cliente upload(MultipartFile archivo, Long id) throws ClienteException {
		Cliente cliente = findById(id);
		deleteImg(cliente);
		// El UUID es para generar un identificar random único.
		String nombreArchivo = UUID.randomUUID().toString() + archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
		log.info(rutaArchivo.toString());
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
		} catch (IOException e) {
			throw new ClienteException("-1", "Ocurrió un error al guardar la imagen: " + nombreArchivo);
		}

		cliente.setFoto(nombreArchivo);
		save(cliente);
		return cliente;
	}

	private void deleteImg(Cliente cliente) throws ClienteException {
		String fotoAnterior = cliente.getFoto();
		if (fotoAnterior != null && fotoAnterior.length() > 0) {
			Path rutaArchivoAnterior = Paths.get("uploads").resolve(fotoAnterior).toAbsolutePath();
			File archivoAnterior = rutaArchivoAnterior.toFile();
			if (archivoAnterior.exists() && archivoAnterior.canRead()) {
				archivoAnterior.delete();
			}

		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return dao.findAllRegiones();
	}

	@Override
	public Factura findFacturaById(Long id) {
	    return facturaDao.findById(id).orElse(null);
	}

	@Override
	public Factura saveFactura(Factura factura) {
	    return facturaDao.save(factura);
	}

	@Override
	public void deleteFacturaById(Long id) {
	    facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findProductoByNombre(String nombre) {
	    return productoDao.findByNombreContainingIgnoreCase(nombre);
	}

}
