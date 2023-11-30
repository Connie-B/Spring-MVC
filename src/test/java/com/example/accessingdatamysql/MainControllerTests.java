package com.example.accessingdatamysql;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTests {
    
    // these tests do not start the server at all but test only the layer below that
	@Autowired 	private MockMvc mockMvc;
    @MockBean 	private AppUserRepository appUserRepository;


    @Test
	void shouldReturnSavedMessage() throws Exception {
        when(appUserRepository.save(any(AppUser.class)))
                .thenAnswer(i -> i.getArguments()[0]);
		this.mockMvc.perform(post("/demo/add?name=AnyName&email=anyname@anyemailprovider.com")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Saved")));
	}

    @Test
	void shouldReturnIterableOfAppUsers() throws Exception {
        AppUser oneUser = new AppUser(12, "anyName", "anyEmail@isp.com");
        AppUser twoUser = new AppUser(86, "Shim Sham", "nosuchemail@isp.org");
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<AppUser> users = new ArrayList<AppUser>();
        users.add(oneUser);
        users.add(twoUser);
        when(appUserRepository.findAll())
                .thenReturn(users);
		this.mockMvc.perform(get("/demo/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(users)));
	}

    @Test
	void shouldReturnOneAppUser() throws Exception {
        Integer idnumber = 2;
        AppUser testUser = new AppUser(idnumber, "theName", "theEmail");
        when(appUserRepository.findById(any(Integer.class)))
                .thenReturn(Optional.of(testUser));
		this.mockMvc.perform(get("/demo/"+idnumber)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(testUser.toString()));
	}

    @Test
	void shouldReturnHelloMessage() throws Exception {
		this.mockMvc.perform(get("/demo/hello")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello from MainController")));
	}
}
