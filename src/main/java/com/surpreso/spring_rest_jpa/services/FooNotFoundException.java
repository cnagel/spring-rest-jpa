package com.surpreso.spring_rest_jpa.services;

import com.surpreso.spring_rest_jpa.beans.Foo;

/**
 * An Exception if there exists no {@link Foo} for a given search criteria.
 * 
 * @author Christoph Nagel
 */
public class FooNotFoundException extends Exception {

	private static final long serialVersionUID = -7296989821135316258L;

	/**
	 * @param fooId
	 *            The ID of the missing {@link Foo}
	 */
	public FooNotFoundException(Long fooId) {
		super("Foo with id '" + fooId + "' not found");
	}

}
