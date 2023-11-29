package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller 
@RequestMapping(path="/demo") 
public class MainController {
    // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
  @Autowired 
  private AppUserRepository appUserRepository;

  // Map ONLY POST Requests
  @PostMapping(path="/add") 
  // @ResponseBody means the returned String is the response, not a view name
  public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
    AppUser n = new AppUser();
    n.setName(name);
    n.setEmail(email);
    appUserRepository.save(n);
    return "Saved";
  }

  // This returns a JSON or XML with the users
  @GetMapping(path="/all")
  public @ResponseBody Iterable<AppUser> getAllUsers() {
    return appUserRepository.findAll();
  }

  @GetMapping(path="/{id}")
  public @ResponseBody AppUser getOne(@PathVariable Integer id) {

      return appUserRepository.findById(id)
          .map(user -> user) // .map(quote -> new QuoteResource(quote, "success"))
          .orElse(new AppUser()); // .orElse(new QuoteResource(NONE, "Quote " + id + " does not exist"));
  }

  @GetMapping(path="/hello")
  public @ResponseBody String sayHello() {
      return "Hello from MainController";
  }

}