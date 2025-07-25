package br.com.desafio01.services;

import br.com.desafio01.entities.Role;
import br.com.desafio01.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByTipoUsuario(String tipoUsuario){
        return roleRepository.findByTipoUsuario(tipoUsuario);
    }
}
