package br.com.desafio01.repository;

import br.com.desafio01.entities.Role;
import br.com.desafio01.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    List<User> findByRole(Optional<Role> role);
}
