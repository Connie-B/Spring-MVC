package com.example.accessingdatamysql;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SampleApplicationTests {

	@Autowired
	private AppUserController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}


	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Simple Spring MVC application");
	}



	// Another approach is to not start the server at all but to test only the layer below that
	// import static org.hamcrest.Matchers.containsString;
	// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
	// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
	// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
	// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
	// add on class: // @AutoConfigureMockMvc
	// @Autowired
	// private MockMvc mockMvc;

	// @Test
	// void shouldReturnDefaultMessage() throws Exception {
	// 	this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
	// 			.andExpect(content().string(containsString("Simple Spring MVC application")));
	// }

}
