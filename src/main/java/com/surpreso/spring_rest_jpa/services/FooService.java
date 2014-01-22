package com.surpreso.spring_rest_jpa.services;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.surpreso.spring_rest_jpa.beans.Foo;
import com.surpreso.spring_rest_jpa.dao.FooRepository;

/**
 * The CRUD service for {@link Foo} beans. Throws a {@link FooNotFoundException}
 * if there exists no entity for the given ID.
 * 
 * @author Christoph Nagel
 */
@Component
@Transactional
public class FooService {

	@Inject
	private FooRepository repo;

	/**
	 * Returns all existing entities.
	 * 
	 * @return All entities
	 */
	public Iterable<Foo> index() {
		return repo.findAll();
	}

	/**
	 * Creates a new entity
	 * 
	 * @param foo
	 *            The entity to create
	 * @return The created entity
	 */
	public Foo create(Foo foo) {
		return repo.save(foo);
	}

	/**
	 * Returns an entity for the given ID.
	 * 
	 * @param id
	 *            The ID of the entity
	 * @return The entity
	 * @throws FooNotFoundException
	 *             Thrown if there is no entity with the given ID
	 */
	public Foo read(Long id) throws FooNotFoundException {
		if (!repo.exists(id)) {
			throw new FooNotFoundException(id);
		}
		return repo.findOne(id);
	}

	/**
	 * Updates the values of an entity matching the given ID.
	 * 
	 * @param id
	 *            The ID of the existing entity
	 * @param foo
	 *            The new entity
	 * @return The stored entity
	 * @throws FooNotFoundException
	 *             Thrown if there is no entity with the given ID
	 */
	public Foo update(Long id, Foo foo) throws FooNotFoundException {
		Foo oldFoo = read(id);
		oldFoo.setName(foo.getName());
		return repo.save(oldFoo);
	}

	/**
	 * Deletes an entity for the given ID.
	 * 
	 * @param id
	 *            The ID of the entity
	 * @throws FooNotFoundException
	 *             Thrown if there is no entity with the given ID
	 */
	public void delete(Long id) throws FooNotFoundException {
		if (!repo.exists(id)) {
			throw new FooNotFoundException(id);
		}
		repo.delete(id);
	}

}
