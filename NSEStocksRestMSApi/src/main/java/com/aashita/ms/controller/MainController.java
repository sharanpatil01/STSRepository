package com.aashita.ms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	

// This means to get the bean called userRepository
// Which is auto-generated by Spring, we will use it to handle the data
  @Autowired
	public PersonsRepository personsRepository;

  @PostMapping(path="/persons/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Persons n = new Persons();
    n.setFname(name);
    n.setEmail(email);
    n.setLname("B.");
    personsRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/persons/all")
  public @ResponseBody Iterable<Persons> getAllUsers() {
    // This returns a JSON or XML with the users
    return personsRepository.findAll();
  }
  
}
