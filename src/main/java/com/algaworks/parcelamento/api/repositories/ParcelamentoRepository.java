package com.algaworks.parcelamento.api.repositories;

import com.algaworks.parcelamento.api.domain.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Long>, JpaSpecificationExecutor<Parcelamento> {


}
