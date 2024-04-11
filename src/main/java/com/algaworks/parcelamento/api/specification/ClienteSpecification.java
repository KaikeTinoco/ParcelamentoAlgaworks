package com.algaworks.parcelamento.api.specification;

import com.algaworks.parcelamento.api.domain.model.Cliente;
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
public class ClienteSpecification implements Specification<Cliente> {

    private  SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase("%")) {
            //aqui ele só tá testando o que chegou para retornar
            if ("nome".equals(criteria.getKey())) {
                return builder.like(
                        root.get("nome"), "%" + criteria.getValue() + "%");
            } else if ("email".equals(criteria.getKey())) {
                return builder.equal(
                        root.get("email"), criteria.getValue());
            } else if ("numeroTelefone".equals(criteria.getKey())) {
                return builder.equal(
                        root.get("numeroTelefone"), criteria.getValue());
            }  else {
                return null;
            }
        }
        return null;
    }

    @Override
    public Specification and(Specification other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification or(Specification other) {
        return Specification.super.or(other);
    }
}
