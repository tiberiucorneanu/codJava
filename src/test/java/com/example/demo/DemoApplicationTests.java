package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.UserController;
import com.example.demo.entities.User;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	
	@MockBean
	private UserController controler;
	
	@Test
	public void getUsersTest() {
		List<User> usersTest = new ArrayList<>();
		usersTest.add(new User((long) 1, "Tiberiu","Corneanu","corneanu@gmail.com","443D","DescriereTibi","parolaTibi"));
		usersTest.add(new User((long) 2, "Tiberiu2","Corneanu2","corneanu2@gmail.com","443D2","DescriereTibi2","parolaTibi2"));
		when(controler.list()).thenReturn(ResponseEntity.ok().body(usersTest));
		System.out.println("Metoda getUsersTest()" + ResponseEntity.ok().body(usersTest));
		assertEquals(2, controler.list().getBody().size());
	}
	
	@Test
	public void getUserByIdTest() {
		Long id = (long) 1;
		User user = new User(id, "Tiberiu","Corneanu","corneanu@gmail.com","443D","DescriereTibi","parolaTibi");
		when(controler.get(Mockito.anyLong())).thenReturn(ResponseEntity.ok().body(user));
		System.out.println("Metoda getUserByIdTest() are Body " + user.toString());
		System.out.println("Metoda getUserByIdTest() are Body " + controler.get(id).getBody());
		assertEquals(user, controler.get(id).getBody());
	}
	
	@Test
	public void deleteUserTest() {
		Long id = (long) 1;
		User user = new User(id, "Tiberiu","Corneanu","corneanu@gmail.com","443D","DescriereTibi","parolaTibi");
		//RestTemplate restTemplate = new RestTemplate();
		//ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8080/api/user/"+id, String.class);
		when(controler.delete(id)).thenReturn("Test Stergere reusit");
		System.out.println("Metoda deleteUserTest() " + controler.delete(id));
		assertEquals("Test Stergere reusit", controler.delete(id));
		//verify(repository, times(1)).delete(user);
	}

}
