package com.example.demo.service;

import com.example.demo.common.Constant;
import com.example.demo.specification.CustomSpecification;
import com.example.demo.specification.Expression;
import com.example.demo.specification.filter.DateFilter;
import com.example.demo.specification.filter.Filter;
import com.example.demo.specification.filter.IntegerFilter;

import com.example.demo.specification.filter.StringFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class QueryService<T> {
    public Specification<T> buildIntegerFilter(String field, IntegerFilter filter) {
        if (filter.getGreaterThan() != null){
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.GREATER_THAN, filter.getGreaterThan()));
        }
        if (filter.getGreaterThanEqual() != null){
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.GREATER_THAN_EQUAL, filter.getGreaterThanEqual()));
        }
        if (filter.getLessThan() != null){
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.LESS_THAN, filter.getLessThan()));
        }
        if (filter.getLessThanEqual() != null){
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.LESS_THAN_EQUAL, filter.getLessThanEqual()));
        }
        return buildCommonFilter(field, filter);
    }

    public Specification<T> buildStringFilter(String field, StringFilter filter) {
        if (filter.getContains() != null) {
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.CONTAINS, filter.getContains()));
        }
        if (filter.getNotContains() != null){
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.NOT_CONTAINS, filter.getNotContains()));
        }
        return buildCommonFilter(field, filter);
    }
    public Specification<T> buildDateFilter(String field, DateFilter filter){
        if (filter.getGreaterThan() != null){
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.GREATER_THAN, filter.getGreaterThan()));
        }
        if (filter.getGreaterThanEqual() != null){
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.GREATER_THAN_EQUAL, filter.getGreaterThanEqual()));
        }
        if (filter.getLessThan() != null){
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.LESS_THAN, filter.getLessThan()));
        }
        if (filter.getLessThanEqual() != null){
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.LESS_THAN_EQUAL, filter.getLessThanEqual()));
        }
        return buildCommonFilter(field, filter);
    }
    private Specification<T> buildCommonFilter(String field, Filter filter) {
        if (filter.getEquals() != null) {
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.EQUALS, filter.getEquals()));
        }
        if (filter.getNotEquals() != null) {
            return new CustomSpecification<>(new Expression(field, Constant.OPERATOR.NOT_EQUALS, filter.getNotEquals()));
        }
        return null;
    }


}
