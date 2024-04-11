package com.algaworks.parcelamento.api.specification;

import com.algaworks.parcelamento.api.domain.model.Parcelamento;
import com.algaworks.parcelamento.api.specification.criteria.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcelamentoSpecification implements Specification<Parcelamento> {
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Parcelamento> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase("%")) {
            //aqui ele só tá testando o que chegou para retornar
            if ("nome".equals(criteria.getKey())) {
                return builder.like(
                        root.join("cliente").get("nome"), "%" + criteria.getValue() + "%");
            } else if ("email".equals(criteria.getKey())) {
                return builder.equal(
                        root.join("cliente").get("email"), criteria.getValue());
            } else if ("numeroTelefone".equals(criteria.getKey())) {
                return builder.equal(
                        root.join("cliente").get("numeroTelefone"), criteria.getValue());
            }  else if ("descrição".equals(criteria.getKey())){
                return builder.like(root.get("descricao"), "%" + criteria.getValue() + "%");
            } else if ("valorTotal".equals(criteria.getKey())){
                return builder.like(root.get("valorTotal"), "==" + criteria.getValue());
            } else if("quantidadeParcelas".equals(criteria.getKey())) {
                return builder.like(root.get("quantidadeParcelas"), "==" + criteria.getValue());
            }
        }
        return null;
    }
}
