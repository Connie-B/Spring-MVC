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
public class AppUserControllerTests {
    
    // these tests do not start the server at all but test only the layer below that
    @Autowired 	private MockMvc mockMvc;
    @MockBean 	private AppUserRepository appUserRepository;


    @Test
    void shouldReturnIterableOfAppUsers() throws Exception {
        AppUser oneUser = new AppUser("Any", "Name", "123 Main st.", "Citytown", "1234567890", "anyEmail@isp.com");
        AppUser twoUser = new AppUser("Shim", "Sham", "23 skidoo", "Shamtown", "4564564567", "nosuchemail@isp.org");
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<AppUser> users = new ArrayList<AppUser>();
        users.add(oneUser);
        users.add(twoUser);
        when(appUserRepository.findAll())
                .thenReturn(users);
                this.mockMvc.perform(get("/contacts/all")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(users)));
    }


    @Test
    void shouldReturnOneAppUser() throws Exception {
        Integer idnumber = 2;
        AppUser testUser = new AppUser("theFirstName", "theLastName", "123 main st.", "city", "1234567890", "theEmail");
        when(appUserRepository.findById(any(Integer.class)))
                .thenReturn(Optional.of(testUser));
		this.mockMvc.perform(get("/contacts/" + idnumber)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(testUser.toString()));
    }


}
