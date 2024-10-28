package com.mycompany.mywebapp.Repository;

import com.mycompany.mywebapp.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);

    Optional<User> findByEmail(String email);
}
