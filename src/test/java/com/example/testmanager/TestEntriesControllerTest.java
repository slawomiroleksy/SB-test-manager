package com.example.testmanager;

import static com.example.testmanager.Status.FAILED;
import static com.example.testmanager.Status.PASSED;
import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestEntriesControllerTest {

	private TestEntry test;

	@Autowired
	private TestEntryRepository repository;
	@Autowired
	private MockMvc mockMvc;


	@BeforeEach
	private void setUp() {
		test = new TestEntry();
		test.setName("Test name");
		test.setStatus(PASSED);

		repository.save(test);
	}

	@AfterEach
	private void tearDown() {
		repository.deleteAll();
	}

	@Test
	public void getMethodReturnsListOfTests() throws Exception {
		this.mockMvc.perform(get("/tests")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Test name")))
				.andExpect(jsonPath("$[0].status", is(PASSED.toString())));
	}

	@Test
	public void postMethodReturnsBadRequestStatusWhenRequestBodyIsMissingName() throws Exception {
		this.mockMvc.perform(post("/tests")
				.contentType(MediaType.APPLICATION_JSON)
				.content(format("{\"status\": \"%s\"}", PASSED)))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void postMethodReturnsBadRequestStatusWhenRequestBodyIsMissingStatus() throws Exception {
		this.mockMvc.perform(post("/tests")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"Test name\"}"))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void postMethodReturnsCreatedStatus() throws Exception {
		this.mockMvc.perform(post("/tests")
				.contentType(MediaType.APPLICATION_JSON)
				.content(format("{\"name\": \"Test name\", \"status\": \"%s\"}", PASSED)))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void putMethodReturnsUpdatedStatus() throws Exception {
		this.mockMvc.perform(put("/tests/" + test.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(format("{\"name\": \"Test name updated\", \"status\": \"%s\"}", FAILED)))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Test name updated")))
				.andExpect(jsonPath("$.status", is(FAILED.toString())));
	}
}
