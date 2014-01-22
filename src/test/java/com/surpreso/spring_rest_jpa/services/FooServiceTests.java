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

import com.google.common.collect.Lists;
import com.surpreso.spring_rest_jpa.DefaultConfig;
import com.surpreso.spring_rest_jpa.beans.Foo;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultConfig.class }, loader = SpringApplicationContextLoader.class)
public class FooServiceTests {

	@Inject
	private FooService service;

	@Test
	public void test_index() {
		ArrayList<Foo> foos = Lists.newArrayList(service.index());
		assertEquals(2, foos.size());
		assertEquals(new Long(1l), foos.get(0).getId());
	}

}
