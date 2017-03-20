package org.proj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.proj.app.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findOneByUsername(String username);
	User findById(Long id);

}
