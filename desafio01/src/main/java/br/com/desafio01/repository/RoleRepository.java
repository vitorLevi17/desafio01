package br.com.desafio01.repository;

import br.com.desafio01.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {


    Role findByTipoUsuario(String tipoUsuario);
}
