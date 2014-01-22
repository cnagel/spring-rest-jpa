package com.surpreso.spring_rest_jpa.controller;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surpreso.spring_rest_jpa.DefaultConfig;
import com.surpreso.spring_rest_jpa.beans.Foo;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultConfig.class }, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@Transactional
public class FooControllerTests {

	@Inject
	private WebApplicationContext ctx;
	@Inject
	private ObjectMapper objectMapper;

	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void test_index() throws Exception {
		this.mvc.perform(get("/foos"))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string("[{\"id\":1,\"name\":\"Foo 1\"},{\"id\":9,\"name\":\"Foo 9\"}]"));
	}

	@Test
	public void test_create() throws Exception {

		Foo foo = new Foo();
		foo.setName("Foo 35");
		mvc.perform(
				post("/foos").contentType(MediaType.APPLICATION_JSON).content(
						objectMapper.writeValueAsString(foo)))
				.andExpect(status().isOk())
				.andExpect(
						content().string(
								"{\"id\":10,\"name\":\"" + foo.getName()
										+ "\"}"));
	}

	@Test
	public void test_show() throws Exception {
		mvc.perform(get("/foos/9")).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":9,\"name\":\"Foo 9\"}"));
	}

	@Test
	public void test_show_fooNotFound() throws Exception {
		mvc.perform(get("/foos/47578")).andExpect(status().isNotFound())
				.andExpect(content().string(""));
	}

	@Test
	public void test_update() throws Exception {
		Foo foo = new Foo();
		foo.setId(9l);
		foo.setName("Foo 9 new");
		mvc.perform(
				put("/foos/9").contentType(MediaType.APPLICATION_JSON).content(
						objectMapper.writeValueAsString(foo)))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string("{\"id\":9,\"name\":\"" + foo.getName()
										+ "\"}"));
	}

	@Test
	public void test_update_fooNotFound() throws Exception {
		Foo foo = new Foo();
		foo.setId(45l);
		foo.setName("Foo 45 new");
		mvc.perform(
				put("/foos/45").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(foo)))
				.andExpect(status().isNotFound())
				.andExpect(content().string(""));
	}

	@Test
	public void test_delete() throws Exception {
		mvc.perform(delete("/foos/9")).andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	public void test_delete_fooNotFound() throws Exception {
		mvc.perform(delete("/foos/4853245")).andExpect(status().isNotFound())
				.andExpect(content().string(""));
	}

}
