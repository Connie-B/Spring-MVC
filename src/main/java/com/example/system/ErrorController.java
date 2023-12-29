package com.example.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller 
public class ErrorController {
  
  @GetMapping(path="/error")
  public @ResponseBody String handleError() {
      return "error";
  }
}
