package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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

import com.example.demo.entities.User;
import com.example.demo.entities.VirtualMachine;
import com.example.demo.repo.VirtualMachineRepo;

@CrossOrigin(origins = "*")
@RestController         //rest API
@RequestMapping("/api")
public class VirtualMachineController {

	@Autowired
	private VirtualMachineRepo repo;
	
	@Autowired
	private JpaRepository<VirtualMachine, ?> repository;
	
	 /*---Add new EnvironmentVM---*/
	 @PostMapping("/environment")
	 public ResponseEntity<?> save(@RequestBody VirtualMachine vm) throws IOException {
		vm.setStatus("Crează..");
		long id = repo.save(vm).getId();
	    CreateVirtualMachine cvm = new CreateVirtualMachine();
		cvm.createVM(vm);
		vm.setStatus("În execuție");
		repo.save(vm);
	    return ResponseEntity.ok().body("O nouă mașină virtuală a fost salvată având id-ul: " + id);
	 }
	 
	 /*---get all EnvironmentVM---*/
	 @GetMapping("/environment")
	 public ResponseEntity<List<VirtualMachine>> list() {
	    List<VirtualMachine> vms = repo.findAll();
	    return ResponseEntity.ok().body(vms);
	 }
	 
	 /*---Get a EnvironmentVM by id---*/
	 @GetMapping("/environment/{id}")		//http request  @PathVariable("id")= {id}
	 public ResponseEntity<VirtualMachine> get(@PathVariable("id") long id) {
		VirtualMachine vm = repo.getOne(id);
	    System.out.println("user: " + vm);
	    return ResponseEntity.ok().body(vm);
	 }
	 
	 /*---Update a EnvironmentVM by id---*/
	   @PutMapping("/environment/update/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody VirtualMachine vm) throws IOException {
		   Optional<VirtualMachine> vmOptional = repo.findById(id);
		   if(vmOptional.isPresent()) {
			   VirtualMachine oldVM = repo.getOne(id);
			   oldVM.setStatus("Actualizare..");
			   StopVirtualMachine stop = new StopVirtualMachine();
			   stop.suspendVM(oldVM.getName());
			   
			   vm.setId(id);
			   System.out.println("----------Modificarea numelui directorului---------");
				File dir = new File("C:\\Users\\tcorneanu\\Desktop\\Licenta\\MasinileVirtuale\\" + oldVM.name);
		        File newName = new File("C:\\Users\\tcorneanu\\Desktop\\Licenta\\MasinileVirtuale\\" + vm.name);
		        if ( dir.isDirectory() ) {
		                dir.renameTo(newName);
		        } else {
		                dir.mkdir();
		                dir.renameTo(newName);
		        }
			   
			   UpdateVirtualMachine update = new UpdateVirtualMachine();
			   update.updateVM(vm, oldVM);
			   
			   vm.setStatus("În execuție");
			   repo.save(vm);
			   
			   
			   
		   }else {
			   throw new IllegalArgumentException("vmOptional with id = " + id + " is Empty!");
		   }
		  System.out.println("EnvironmentVM a fost actualizat cu succes. vm = "+vm); 
	      return ResponseEntity.ok().body("EnvironmentVM a fost actualizat cu succes. vm = "+vm);
	   }

	   /*---Start a EnvironmentVM by id---*/
	   @GetMapping(value = "/environment/start/{id}")
	   public String start(@PathVariable("id") long id) throws IOException {
		   VirtualMachine vm = repo.getOne(id);
		   vm.setStatus("Pornește..");
		   repo.save(vm);
		   
		   StartVirtualMachine start = new StartVirtualMachine();
		   start.startVM(vm.getName());
		   
		   vm.setStatus("În execuție");
		   repo.save(vm);
		   return "Mașina virtuală " + vm.toString() + " a pornit cu succes";
	   }
	   
	   /*---Stop a EnvironmentVM by id---*/
	   @GetMapping(value = "/environment/suspend/{id}")
	   public String suspend(@PathVariable("id") long id) throws IOException {
		   VirtualMachine vm = repo.getOne(id);
		   vm.setStatus("Suspendă..");
		   repo.save(vm);
		   
		   StopVirtualMachine stop = new StopVirtualMachine();
		   stop.suspendVM(vm.getName());
		   
		   vm.setStatus("Suspendat");
		   repo.save(vm);
		   return "Mașina " + vm.toString() + " a fost suspendată";
	   }
	   
	   /*---Delete a EnvironmentVM by id---*/
	   @DeleteMapping(value = "/environment/delete/{id}")
	   public String delete(@PathVariable("id") long id) throws IOException {
		   VirtualMachine vm = repo.getOne(id);
		   vm.setStatus("Sterge..");
		   DeleteVirtualMachine deleteVM = new DeleteVirtualMachine();
		   System.out.println("\n\n\n\n Șterge " + vm.name+"---" +vm.name+"----");
		   deleteVM.destroyVM(vm.getName());
		   repository.delete(vm);
		   return "Mașina virtuală " + vm.toString() + " a fost ștearsă cu succes!";
	   }
}
