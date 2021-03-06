package com.damb.springboot.backend.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.damb.springboot.backend.apirest.models.entity.Factura;
import com.damb.springboot.backend.apirest.models.entity.Producto;
import com.damb.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class FacturaRestController {
    @Autowired
    private IClienteService clienteService;

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @GetMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Factura show(@PathVariable Long id) {
	return clienteService.findFacturaById(id);
    }

    @Secured({ "ROLE_ADMIN" })
    @DeleteMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
	clienteService.deleteFacturaById(id);
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @GetMapping("/facturas/filtrar-productos/{term}")
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> filterProducts(@PathVariable String term) {
	return clienteService.findProductoByNombre(term);
    }

    @Secured({ "ROLE_ADMIN" })
    @PostMapping("/facturas")
    @ResponseStatus(HttpStatus.CREATED)
    public Factura crear(@RequestBody Factura factura) {
	System.out.println(factura.toString());
	return clienteService.saveFactura(factura);
    }

    /*
     * @GetMapping("/test")
     * 
     * @ResponseStatus(HttpStatus.OK) public String test() { int array[] = { 10, 3,
     * 5, 6, 2 }; int array2[] = new int[array.length]; for (int i = 0; i <
     * array.length; i++) { int result = 0; for (int j = 0; j < array.length; j++) {
     * if(j==i) { continue; } result = result * array[j]; } array2[i] = result; }
     * return response.toString(); }
     */
}
