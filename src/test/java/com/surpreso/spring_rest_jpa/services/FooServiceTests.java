package com.surpreso.spring_rest_jpa.services;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.surpreso.spring_rest_jpa.DefaultConfig;
import com.surpreso.spring_rest_jpa.beans.Foo;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultConfig.class }, loader = SpringApplicationContextLoader.class)
@Transactional
public class FooServiceTests {

	@Inject
	private FooService service;

	@Test
	public void test_index() {
		ArrayList<Foo> foos = Lists.newArrayList(service.index());
		assertEquals(2, foos.size());
		assertEquals(new Long(1l), foos.get(0).getId());
		assertEquals("Foo 1", foos.get(0).getName());
	}

	@Test
	public void test_create() {
		Foo foo = new Foo();
		foo.setName("Foo 10");
		Foo createdFoo = service.create(foo);
		assertEquals(foo.getName(), createdFoo.getName());
		assertNotNull(createdFoo.getId());
	}

	@Test
	public void test_read() throws FooNotFoundException {
		Foo foo = service.read(1l);
		assertEquals(new Long(1l), foo.getId());
		assertEquals("Foo 1", foo.getName());
	}

	@Test(expected = FooNotFoundException.class)
	public void test_read_fooNotFound() throws FooNotFoundException {
		service.read(3482394l);
	}

	@Test
	public void test_update() throws FooNotFoundException {
		Foo foo = new Foo();
		foo.setName("Foo 9 new");
		Foo updatedFoo = service.update(9l, foo);
		assertEquals(new Long(9l), updatedFoo.getId());
		assertEquals(foo.getName(), updatedFoo.getName());
	}

	@Test(expected = FooNotFoundException.class)
	public void test_update_fooNotFound() throws FooNotFoundException {
		Foo foo = new Foo();
		foo.setName("Foo 9 new");
		service.update(3482394l, foo);
	}

	@Test
	public void test_delete() throws FooNotFoundException {
		service.delete(1l);
	}

	@Test(expected = FooNotFoundException.class)
	public void test_delete_fooNotFound() throws FooNotFoundException {
		service.delete(473259l);
	}

}
