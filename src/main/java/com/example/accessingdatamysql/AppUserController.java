package com.example.accessingdatamysql;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;


@Controller 
@RequestMapping(path="/clients") 
public class AppUserController {

  @Autowired 
  private AppUserRepository appUserRepository;

  @GetMapping(value={"","/"})
	public String welcome() {
		return "welcome";
	}
  
  @GetMapping(path="/all")
  public @ResponseBody Iterable<AppUser> getAllUsers() {
    return appUserRepository.findAll();
  }

  @GetMapping(path="/{id}")
  public @ResponseBody AppUser getUserById(@PathVariable("id") int appUserId) {
    Optional<AppUser> appUser = this.appUserRepository.findById(appUserId);
    if( appUser.isPresent() ) {
      return appUser.get();
    } else {
      return new AppUser();
    }
  }


  @GetMapping("/listAll")
  public String getAllAppUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pagesize, Model model) {
    Page<AppUser> paginatedResults = findAllPaginated(page, pagesize);
    return addPaginationModel(model, paginatedResults, new AppUser());
  }
  private Page<AppUser> findAllPaginated(int page, int pageSize) {
    Pageable pageable = PageRequest.of(page-1, pageSize);
    return appUserRepository.findAll(pageable);
  }
  private String addPaginationModel(Model model, Page<AppUser> paginated, AppUser searchedFor) {
    model.addAttribute("paginated", paginated);
    model.addAttribute("appUser", searchedFor);
    return "clients/clientsList";
  }

  @GetMapping("/find")
  public String initFindForm(Model model) {
    AppUser appUser = new AppUser();
    model.addAttribute("appUser", appUser);
    return "clients/clientsSearch";
  }
  @GetMapping("/findclients")
  public String processFindForm(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pagesize, 
      AppUser appUser, BindingResult result, Model model) {
    if (null == appUser.getFirstName()) {
      appUser.setFirstName("");
    }
    if (null == appUser.getLastName()) {
      appUser.setLastName("");
    }
    if (null == appUser.getAddress()) {
      appUser.setAddress("");
    }
    if (null == appUser.getCity()) {
      appUser.setCity("");
    }
    if (null == appUser.getTelephone()) {
      appUser.setTelephone("");
    }
    if (null == appUser.getEmail()) {
      appUser.setEmail("");
    }
    String firstNameFindme = appUser.getFirstName().trim().toLowerCase();    
    String lastNameFindme = appUser.getLastName().trim().toLowerCase();
    String addressFindme = appUser.getAddress().trim().toLowerCase();
    String cityFindme = appUser.getCity().trim().toLowerCase();
    String telephoneFindme = appUser.getTelephone().trim().toLowerCase();
    String emailFindme = appUser.getEmail().trim().toLowerCase();

    // find clients by searching
    Page<AppUser> paginatedResults = findPaginatedForClientSearch(page, pagesize, firstNameFindme, lastNameFindme, 
      addressFindme, cityFindme, telephoneFindme, emailFindme);
    if (paginatedResults.isEmpty()) {
      // no clients found
      model.addAttribute("message", "Client not found");
      model.addAttribute("alertClass", "alert-info");
      return "clients/clientsSearch";
    }
    if (paginatedResults.getTotalElements() == 1) {
      // 1 client found
      appUser = paginatedResults.iterator().next();
      return "redirect:/clients/clientDetails/" + appUser.getId();
    }
    // multiple clients found
    return addPaginationModel(model, paginatedResults, appUser);
  }
  private Page<AppUser> findPaginatedForClientSearch(int page, int pageSize, String firstname, String lastname, 
      String address, String city, String telephone, String email) {
    Pageable pageable = PageRequest.of(page - 1, pageSize);
    Page<AppUser> thePage = appUserRepository.findByDetails(firstname, lastname, address, city, telephone, email, pageable);
    return thePage;
  }




  @GetMapping("/new")
  public String createNewUserForm(Model model) {
    AppUser appUser = new AppUser();
    model.addAttribute("appUser", appUser);
    model.addAttribute("action", "create");
    return "clients/clientsCreateOrEditForm";
  }
  @PostMapping("/new")
  public String createNewUser(@Valid AppUser appUser, BindingResult result, Model model, RedirectAttributes redirAttr) {
    if (result.hasErrors()) {
      model.addAttribute("appUser", appUser);
      model.addAttribute("action", "create");
      return "clients/clientsCreateOrEditForm";
    } else {
      this.appUserRepository.save(appUser);
      redirAttr.addFlashAttribute("message", "New client added");
      redirAttr.addFlashAttribute("alertClass", "alert-success");
      return "redirect:/clients/clientDetails/" + appUser.getId();
    }
  }


  @GetMapping("/clientDetails/{id}")
  public String showAppUserDetails(@PathVariable("id") int appUserId, Model model) {
    Optional<AppUser> appUser = this.appUserRepository.findById(appUserId);
    if( appUser.isPresent() ) {
        model.addAttribute(appUser.get());
        model.addAttribute("action", "view");
        return "clients/clientsCreateOrEditForm";
    } else {
      // client not found
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/clientDetails/{id}")
  public String redirShowAppUserDetails(@PathVariable("id") int appUserId, Model model) {
        return "redirect:/clients/clientDetails/{id}";
  }


  @GetMapping("/clientDetails/{id}/edit")
  public String initUpdateAppUserForm(@PathVariable("id") int appUserId, Model model) {
    Optional<AppUser> appUser = this.appUserRepository.findById(appUserId);
    if( appUser.isPresent() ) {
        model.addAttribute(appUser.get());
        model.addAttribute("action", "edit");
        return "clients/clientsCreateOrEditForm";
    } else {
      // client not found
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/clientDetails/{id}/edit")
  public String processUpdateAppUserForm(@Valid AppUser appUser, BindingResult result, @PathVariable("id") int appUserId, Model model, RedirectAttributes redirAttr) {
    if (result.hasErrors()) {
      model.addAttribute("appUser", appUser);
      model.addAttribute("action", "edit");
      return "clients/clientsCreateOrEditForm";
    } else {
      // result.getTarget();
      appUser.setId(appUserId);
      this.appUserRepository.save(appUser);
      redirAttr.addFlashAttribute("message", "Client updated");
      redirAttr.addFlashAttribute("alertClass", "alert-success");
      return "redirect:/clients/clientDetails/{id}";
    }
  }

}
