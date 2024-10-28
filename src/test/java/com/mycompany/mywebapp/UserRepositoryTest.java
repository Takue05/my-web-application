package com.mycompany.mywebapp;

import com.mycompany.mywebapp.Model.User;
import com.mycompany.mywebapp.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;


  @BeforeEach
  public void setUp() {
    repo.deleteAll(); // Clear all users before each test
  }


    @Test
    @Transactional
    @Rollback
    public void testAddNew(){
      String email = "jimatinashe@gmail.com";
      Optional<User>existingUser = repo.findByEmail(email);
      existingUser.ifPresent(repo::delete);
        User user = new User();
        user.setEmail("jimatinashe@gmail.com");
        user.setPassword("tj24865");
        user.setFirstName("Tinashe");
        user.setLastName("Jima");

        User savedUser = repo.save(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());

    }
    @Test
    public  void testListAll(){
        Iterable<User>users = repo.findAll();
        Assertions.assertNotNull(users);
        for (User user :users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
      // First, add a new user to ensure there's a user to update
      User user = new User();
      user.setEmail("update_user@gmail.com");
      user.setPassword("ol2342");
      user.setFirstName("Update");
      user.setLastName("User");
      User savedUser = repo.save(user); // Save the user

      Integer userId = savedUser.getId(); // Get the ID of the saved user

      // Now update the user
      User userToUpdate = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
      userToUpdate.setPassword("newpassword");
      repo.save(userToUpdate);

      User updatedUser = repo.findById(userId).orElseThrow();
      Assertions.assertEquals("newpassword", updatedUser.getPassword(), "Passwords did not update correctly");
    }

  @Test
    public void testGet(){
    // Add a user to retrieve
    User user = new User();
    user.setEmail("get_user@gmail.com");
    user.setPassword("pasd123");
    user.setFirstName("Get");
    user.setLastName("User");
    User savedUser = repo.save(user); // Save the user

    Integer userId = savedUser.getId(); // Get the ID of the saved user

    Optional<User> optionalUser = repo.findById(userId);
    Assertions.assertTrue(optionalUser.isPresent(), "User not found");
    System.out.println(optionalUser.get());



    }
    @Test
    public void testDelete(){
      // Add a user to delete
      User user = new User();
      user.setEmail("delete_user@gmail.com");
      user.setPassword("pass1234");
      user.setFirstName("Delete");
      user.setLastName("User");
      User savedUser = repo.save(user); // Save the user

      Integer userId = savedUser.getId(); // Get the ID of the saved user

      // Now delete the user
      if (repo.existsById(userId)) {
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertFalse(optionalUser.isPresent(), "User should be deleted");
      } else {
        System.out.println("User with id " + userId + " does not exist");
      }

    }
}
