package com.learning.coursefun.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.coursefun.entities.Cart;

//@Repository
public interface CartRepo extends CrudRepository<Cart, Integer> {
	
	@Query(nativeQuery = true,value = "select * from cart where user_id=?1")
	public Cart findByUserId(int userId);

}
