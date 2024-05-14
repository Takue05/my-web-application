package com.mycompany.mywebapp.Repository;

import com.mycompany.mywebapp.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
}
