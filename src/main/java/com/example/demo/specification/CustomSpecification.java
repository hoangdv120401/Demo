package com.example.demo.specification;

import com.example.demo.common.Constant;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomSpecification<T> implements Specification<T> {
    private Expression expression;

    @Override
    public Predicate toPredicate(Root<T> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        Predicate predicate = null;
        String field = expression.getField();
        String operator = expression.getOperator();
        Object value = expression.getValue();
        
        switch (operator) {
            case Constant.OPERATOR.EQUALS:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.equal(root.get(field), Integer.valueOf(value.toString()));
                }
                if (value instanceof String) {
                    predicate = criteriaBuilder.equal(root.get(field), String.valueOf(value));
                }
                if (value instanceof Date){
                    predicate = criteriaBuilder.equal(root.get(field).as(java.sql.Date.class),(Date) value);
                }
                break;
            case Constant.OPERATOR.NOT_EQUALS:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.notEqual(root.get(field), Integer.valueOf(value.toString()));
                }
                if (value instanceof String) {
                    predicate = criteriaBuilder.notEqual(root.get(field), String.valueOf(value));
                }
                if (value instanceof Date){
                    predicate = criteriaBuilder.notEqual(root.get(field).as(java.sql.Date.class),(Date) value);
                }
                break;
            case Constant.OPERATOR.CONTAINS:
                if (value instanceof String) {
                    predicate = criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
                }
                break;
            case Constant.OPERATOR.NOT_CONTAINS:
                if (value instanceof String) {
                    predicate = criteriaBuilder.notLike(root.get(field), "%" + value.toString() + "%");
                }
                break;
            case Constant.OPERATOR.GREATER_THAN:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.greaterThan(root.get(field), Integer.valueOf(value.toString()));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.greaterThan(root.get(field).as(java.sql.Date.class), (Date) value);
                }
                break;
            case Constant.OPERATOR.GREATER_THAN_EQUAL:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(field), Integer.valueOf(value.toString()));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(field).as(java.sql.Date.class), (Date) value);
                }
                break;
            case Constant.OPERATOR.LESS_THAN:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.lessThan(root.get(field), Integer.valueOf(value.toString()));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.lessThan(root.get(field).as(java.sql.Date.class), (Date) value);
                }
                break;
            case Constant.OPERATOR.LESS_THAN_EQUAL:
                if (value instanceof Integer) {
                    predicate = criteriaBuilder.lessThanOrEqualTo(root.get(field), Integer.valueOf(value.toString()));
                }
                if (value instanceof Date) {
                    predicate = criteriaBuilder.lessThanOrEqualTo(root.get(field).as(java.sql.Date.class), (Date) value);
                }
                break;
        }
        return predicate;
    }
}
