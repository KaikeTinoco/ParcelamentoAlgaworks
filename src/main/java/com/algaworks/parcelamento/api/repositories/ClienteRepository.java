package com.algaworks.parcelamento.api.repositories;

import com.algaworks.parcelamento.api.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContaining(String nome);
    List<Cliente> findByEmail(String email);
    List<Cliente> findByNumeroTelefone(String numeroTelefone);
}
