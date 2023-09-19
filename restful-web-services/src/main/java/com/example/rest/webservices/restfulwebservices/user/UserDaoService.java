package com.example.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;
	static {
		
		users.add(new User(++usersCount,"Pratik",LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount,"Dipak",LocalDate.now().minusYears(26)));
		users.add(new User(++usersCount,"Patil",LocalDate.now().minusYears(22)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public User createUser(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}

}
