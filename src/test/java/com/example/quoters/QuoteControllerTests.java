package com.example.quoters;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(classes=com.example.accessingdatamysql.AccessingDataMysqlApplication.class)
@AutoConfigureMockMvc
public class QuoteControllerTests {
    
    // these tests do not start the server at all but test only the layer below that
	@Autowired 	private MockMvc mockMvc;
    @MockBean 	private QuoteRepository quoteRepository;


    @Test
	void shouldReturnListOfQuoteResource() throws Exception {
        Quote oneQuote = new Quote("To be or not to be");
        Quote twoQuote = new Quote("Be the change");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Quote> theQuotes = new ArrayList<Quote>();
        theQuotes.add(oneQuote);
        theQuotes.add(twoQuote);
        when(quoteRepository.findAll())
                .thenReturn(theQuotes);
		this.mockMvc.perform(get("/quoter/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(theQuotes
                .stream()
                .map(quote -> new QuoteResource(quote, "success"))
                .collect(Collectors.toList())
                )));
	}

    @Test
	void shouldReturnOneQuoteResource() throws Exception {
        Long idnumber = Long.valueOf(14);
        Quote theQuote = new Quote("We are the world");
        ObjectMapper objectMapper = new ObjectMapper();
        when(quoteRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(theQuote));
		this.mockMvc.perform(get("/quoter/"+idnumber)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(Optional.of(theQuote)
                .map(quote -> new QuoteResource(quote, "success"))
                .orElse(new QuoteResource(new Quote("None"), "Quote " + idnumber + " does not exist"))
                )));
	}

    @Test
	void shouldReturnOneQuoteResourceToo() throws Exception {
        Long idnumber = Long.valueOf(74);
        Quote theQuote = new Quote("I'll gladly pay you Tuesday");
        ObjectMapper objectMapper = new ObjectMapper();
        when(quoteRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(theQuote));
		this.mockMvc.perform(get("/quoter/random")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(Optional.of(theQuote)
                .map(quote -> new QuoteResource(quote, "success"))
                .orElse(new QuoteResource(new Quote("None"), "Quote " + idnumber + " does not exist"))
                )));
	}

    @Test
	void shouldReturnHelloMessage() throws Exception {
		this.mockMvc.perform(get("/quoter/hello")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello from QuoteController")));
	}
}
