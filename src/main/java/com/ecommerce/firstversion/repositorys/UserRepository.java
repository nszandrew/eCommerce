package com.ecommerce.firstversion.repositorys;

import com.ecommerce.firstversion.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
