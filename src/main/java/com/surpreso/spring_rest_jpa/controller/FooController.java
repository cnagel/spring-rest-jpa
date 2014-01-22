package com.surpreso.spring_rest_jpa.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.surpreso.spring_rest_jpa.beans.Foo;
import com.surpreso.spring_rest_jpa.services.FooNotFoundException;
import com.surpreso.spring_rest_jpa.services.FooService;

/**
 * The CRUD controller for the {@link FooService}
 * 
 * @author Christoph Nagel
 */
@RestController
@RequestMapping("/foos")
public class FooController {

	@Inject
	private FooService service;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Foo> index() {
		return service.index();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Foo create(@RequestBody Foo foo) {
		return service.create(foo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Foo show(@PathVariable Long id) throws FooNotFoundException {
		return service.read(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Foo update(@PathVariable Long id, @RequestBody Foo foo)
			throws FooNotFoundException {
		return service.update(id, foo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) throws FooNotFoundException {
		service.delete(id);
	}

	@ExceptionHandler(FooNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	private void handleFooNotFoundException(FooNotFoundException e) {
	}

}