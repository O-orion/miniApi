package br.com.apipostagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apipostagem.models.Usuario;

@Repository
public interface ClienteRepository extends JpaRepository<Usuario, Long> {

}
