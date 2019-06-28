package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entities.User;
import com.example.demo.repo.UserRepo;


@CrossOrigin(origins = "*")
@RestController         //rest API
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private JpaRepository<User, ?> repository;
	
   /*---Add new user---*/
   @PostMapping("/user")
   public ResponseEntity<?> save(@RequestBody User user) {
      long id = repo.save(user).getId();
      return ResponseEntity.ok().body("Un nou user a fost salvat cu ID-ul: " + id);
   }

   /*---Get a user by id---*/
   @GetMapping("/user/{id}")		//http request  @PathVariable("id")= {id}
   public ResponseEntity<User> get(@PathVariable("id") long id) {
      User user = repo.getOne(id);
      System.out.println("user: " + user);
      return ResponseEntity.ok().body(user);
   }

   /*---get all users---*/
   @GetMapping("/user")
   public ResponseEntity<List<User>> list() {
      List<User> users = repo.findAll();
      return ResponseEntity.ok().body(users);
   }

   /*---Update a user by id---*/
   @PutMapping("/user/update/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody User user) {
	   Optional<User> userOptional = repo.findById(id);
	   if(userOptional.isPresent()) {
		   user.setId(id);
		   repo.save(user);
	   }else {
		   throw new IllegalArgumentException("UserOptional cu id = " + id + " este gol ( empty )!");
	   }
	  System.out.println("Utilizatorul a fost actualizat cu succes. useru "+user); 
      return ResponseEntity.ok().body("Utilizatorul a fost actualizat cu succes in baza de date. useru "+user);
   }

   /*---Delete a user by id---*/
   @DeleteMapping(value = "/user/delete/{id}")
   public String delete(@PathVariable("id") long id) {
	   User user = repo.getOne(id);
	   repository.delete(user);
	   return "Utilizatorul " + user.toString() + " a fost șters cu succes!";
   }
}