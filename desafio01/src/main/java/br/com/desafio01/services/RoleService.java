package br.com.desafio01.services;

import br.com.desafio01.entities.Role;
import br.com.desafio01.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findById(Long id){
        return roleRepository.findById(id);
    }
    public Role findByTipoUsuario(String tipoUsuario){
        return roleRepository.findByTipoUsuario(tipoUsuario);
    }
}
