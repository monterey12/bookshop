package com.example.bookshop.repository;

import com.example.bookshop.StudyApplicationTests;
import com.example.bookshop.model.entity.Item;
import com.example.bookshop.model.entity.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {


    @Autowired // Dependency Injection (DI)
    private UserRepository userRepository;

    @Test
    public void create(){
        // String sql = insert into user (%s, %s, %d ) value (account, email, age);
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("new user : "+newUser);

    }

    @Test
    @Transactional
    public void read(){

        // select * from user where id = ?
        Optional<User> user = userRepository.findByAccount("TestUser03");

//        Optional<User> user = userRepository.findById(1L);
//
////        user.ifPresent(selectUser -> {
////            System.out.println("user : " + selectUser);
////            System.out.println("email :"+selectUser.getEmail());
////
////        });
        user.ifPresent(selectUser ->{
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                Item item = detail.getItem();
                System.out.println(item);
            });
        });

    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("TestUser02");
            selectUser.setEmail("TestUser02@gmail.com");
            selectUser.setPhoneNumber("010-1111-2222");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(2L);

        Assertions.assertFalse(deleteUser.isPresent());
    }
}
