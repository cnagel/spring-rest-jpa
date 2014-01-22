package com.surpreso.spring_rest_jpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.surpreso.spring_rest_jpa.beans.Foo;

/**
 * The JPA repository for Foo beans.
 * 
 * @author Christoph Nagel
 */
public interface FooRepository extends CrudRepository<Foo, Long> {

}