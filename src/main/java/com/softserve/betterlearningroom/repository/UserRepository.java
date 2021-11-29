package com.softserve.betterlearningroom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserve.betterlearningroom.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	@Modifying
	@Query("update User u set u.enabled = false where u.id = :id")
	void deactivateUserById(@Param("id") int id);
	
	@Modifying
	@Query("delete User u where u.enabled = false")
	int deleteDeactivatedUsers();

}
