 package com.example.quoters;

 import java.util.List;
 import java.util.Random;
 import java.util.stream.Collectors;
 
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/quoter") 
public class QuoteController {

    private final static Quote NONE = new Quote("None");
    private final static Random RANDOMIZER = new Random();

    @Autowired 
    private QuoteRepository repository;

    @GetMapping(path="/all")
    public @ResponseBody List<QuoteResource> getAll() {

        return repository.findAll().stream()
            .map(quote -> new QuoteResource(quote, "success"))
            .collect(Collectors.toList());
    }

    @GetMapping(path="/{id}")
    public @ResponseBody QuoteResource getOne(@PathVariable Long id) {

        return repository.findById(id)
            .map(quote -> new QuoteResource(quote, "success"))
            .orElse(new QuoteResource(NONE, "Quote " + id + " does not exist"));
    }

    @GetMapping(path="/random")
    public @ResponseBody QuoteResource getRandomOne() {
        return getOne(nextLong(1, repository.count() + 1));
    }
    private long nextLong(long lowerRange, long upperRange) {
        return (long) (RANDOMIZER.nextDouble() * (upperRange - lowerRange)) + lowerRange;
    }

    @GetMapping(path="/hello")
    public @ResponseBody String sayHello() {
        return "Hello from QuoteController";
    }
}
