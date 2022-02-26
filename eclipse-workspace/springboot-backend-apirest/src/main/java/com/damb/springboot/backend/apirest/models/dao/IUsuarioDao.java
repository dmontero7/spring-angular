package com.damb.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.damb.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	public Usuario findByUsername(String username);

//	// ESTO ES SOLO PARA ENTENDER QUE SE PUEDE USAR HQL PARA CONSULTAR
//	@Query("select u from Usuario where u.username = ?1")
//	public Usuario findByUsername2(String username);
}
