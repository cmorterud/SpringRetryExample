package com.cmorterud.examples.spring.retryTemplateExample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class RetryTemplateExampleApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void reliableResourceSucceeds() throws Exception {
		this.mockMvc.perform(get("/resource/getResource")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("Resource"));
	}

	@Test
	void brittleResourceSucceeds() throws Exception {
		this.mockMvc.perform(get("/resource/getBrittleResource")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("Brittle resource"));
	}
}
