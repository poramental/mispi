package com.mispi.mispibot.service.DAO;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;


import com.mispi.mispibot.models.User;
import com.mispi.mispibot.service.DAO.Repositories.UserRepository;

import java.util.List;


@Service
public class UserDAO {

    
    private final UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public User findByUserId(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }


    public void save(User user) {
        userRepository.save(user);
    }

    public boolean isExist(long id) {
        User user = findByUserId(id);
        return user != null;
    }
}