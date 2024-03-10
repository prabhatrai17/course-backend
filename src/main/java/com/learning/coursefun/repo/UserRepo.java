package com.learning.coursefun.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.coursefun.entities.User;



//@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

//	public List<User> allUsers = new ArrayList<>(Arrays.asList(new User(1, "prabhat","p@gmail.com","123", "student"), new User(2, "dharmesh","d@gmail.com","123", "student"),
//			new User(3, "durga","durga@gmail.com","123", "teacher")));
	
//	public User addUser(User user) {
//		//System.out.println(user);
//		//System.out.println(allUsers.add(user));
//        user.setId(allUsers.size()+1);
//		allUsers.add(user);
//		return user;
//		
//	}
	
//	public User getUserById(int id) {
//		for(User user: allUsers) {
//			if(user.getId()==id) return user;
//		}
//		return null;
//	}
//	
//	public User getUserByEmail(String email) {
//		User resUser=null;
//		for(User user:allUsers) {
//			if(user.getEmail().equals(email)) {
//				return user;
//			}
//		}
//		return resUser;
//	}
	
	@Query(value = "select * from users u where u.email=?1",nativeQuery = true)
	User getUserByEmail(String email);

	//User findByEmail(String email);
	
	@Query(value="select * from users where id=?1", nativeQuery=true)
	public User getEnrolledBoughtCoursesOfUser(int userId);
	
//	@Query(value="select * from users where")
//	public User getBoughtCoursesOfUser(int userId);

}
