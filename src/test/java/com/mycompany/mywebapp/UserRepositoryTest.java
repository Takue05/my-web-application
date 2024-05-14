package com.mycompany.mywebapp;

import com.mycompany.mywebapp.Model.User;
import com.mycompany.mywebapp.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;


    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("jimatinashe@gmail.com");
        user.setPassword("tj24865");
        user.setFirstName("Tinashe");
        user.setLastName("Jima");

        User savedUser = repo.save(user);

    }
    @Test
    public  void testListAll(){
        Iterable<User>users = repo.findAll();
        for (User user :users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user =optionalUser.get();
        user.setPassword("password");
        repo.save(user);

        User updatedUser=repo.findById(userId).get();

    }
    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);



    }
    @Test
    public void testDelete(){
        Integer userId = 2;
        repo.deleteById(userId);
        Optional<User> optionalUser=repo.findById(userId);
    }
}
