package com.damb.springboot.backend.apirest.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.damb.springboot.backend.apirest.exceptions.ClienteException;
import com.damb.springboot.backend.apirest.models.entity.Cliente;
import com.damb.springboot.backend.apirest.models.entity.Region;
import com.damb.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {
    @Autowired
    IClienteService clienteService;

    private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page) {
	return clienteService.findAll(PageRequest.of(page, 4));
    }

    @GetMapping("/clientes")
    public List<Cliente> index() {
	return clienteService.findAll();
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> getCliente(@PathVariable Long id) {
	Cliente cliente = null;
	Map<String, Object> response = new HashMap<>();
	try {
	    cliente = clienteService.findById(id);
	} catch (DataAccessException e) {
	    response.put("msg", "Error en la consulta de la base de datos");
	    response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if (cliente == null) {
	    response.put("msg", "El cliente ID:".concat(id.toString()).concat(" no existe en la base de datos"));
	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
	Cliente clienteNew = null;
	Map<String, Object> response = new HashMap<>();
	if (result.hasErrors()) {
	    List<String> errors = result.getFieldErrors().stream()
		    .map(x -> "El campo ".concat(x.getField()).concat(" ").concat(x.getDefaultMessage())).toList();
	    response.put("msg", errors);
	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}

	try {
	    clienteNew = clienteService.save(cliente);
	} catch (DataAccessException e) {
	    response.put("msg", "Ocurrió un error al insertar en la base de datos");
	    response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	response.put("msg", "El cliente ha sido creado con éxito");
	response.put("cliente", clienteNew);
	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
	Map<String, Object> response = new HashMap<>();
	if (result.hasErrors()) {
	    List<String> errors = result.getFieldErrors().stream()
		    .map(x -> "El campo ".concat(x.getField()).concat(" ").concat(x.getDefaultMessage())).toList();
	    response.put("msg", errors);
	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	Cliente clienteActual = clienteService.findById(id);

	if (clienteActual == null) {
	    response.put("msg",
		    "El cliente ID:".concat(id.toString()).concat(" no existe en la base de datos no se pudo editar"));
	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
	}
	Cliente clienteUpdated = null;
	try {
	    clienteActual.setNombre(cliente.getNombre());
	    clienteActual.setApellido(cliente.getApellido());
	    clienteActual.setEmail(cliente.getEmail());
	    clienteActual.setRegion(cliente.getRegion());
	    clienteUpdated = clienteService.save(clienteActual);
	} catch (DataAccessException e) {
	    response.put("msg", "Ocurrió un error al insertar en la base de datos");
	    response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	response.put("msg", "El cliente ha sido modificardo con éxito");
	response.put("cliente", clienteUpdated);
	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
	Map<String, Object> response = new HashMap<>();
	try {
	    clienteService.delete(id);
	} catch (DataAccessException e) {
	    response.put("msg", "Ocurrió un error al eliminar en la base de datos");
	    response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	response.put("msg", "El cliente ha sido eliminado con éxito");
	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile archivo, @RequestParam("id") Long id) {
	Map<String, Object> response = new HashMap<>();
	if (!archivo.isEmpty()) {
	    Cliente cliente;
	    try {
		cliente = clienteService.upload(archivo, id);
		response.put("cliente", cliente);
		response.put("mensaje", "Has subido correctamente la imagen: " + cliente.getFoto());
	    } catch (ClienteException e) {
		response.put("msg", "Ocurrió un error al eliminar en la base de datos");
		response.put("error", e.getMessage().concat(" ").concat(e.getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}
	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // el :.+ es una expresion regular para indicar que el parametro va a recibir
    // una extension de archivo
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
	Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
	log.info(rutaArchivo.toString());
	Resource recurso = null;
	try {
	    recurso = new UrlResource(rutaArchivo.toUri());
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	if (!recurso.exists() && !recurso.isReadable()) {
	    rutaArchivo = Paths.get("src/main/resources/static/images").resolve("profile.png").toAbsolutePath();
	    try {
		recurso = new UrlResource(rutaArchivo.toUri());
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    }
	}
	HttpHeaders cabecera = new HttpHeaders();
	cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + recurso.getFilename() + "\"");
	return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/clientes/regiones")
    public List<Region> getRegiones() {
	return clienteService.findAllRegiones();
    }
}
