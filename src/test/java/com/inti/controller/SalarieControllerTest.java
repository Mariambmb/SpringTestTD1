package com.inti.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inti.repository.ISalarieRepository;

@WebMvcTest(SalarieController.class)
public class SalarieControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private ISalarieRepository isr;
	
	@Test
	@DisplayName("Test d'inscription d'un salarié")
	public void inscriptionSalarie() throws Exception
	{
		mock.perform(get("/inscription"))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
