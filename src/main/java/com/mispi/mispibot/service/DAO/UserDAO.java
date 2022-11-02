package com.mispi.mispibot.service.DAO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import com.mispi.mispibot.models.AppUser;
import com.mispi.mispibot.service.DAO.Repositories.UserRepository;

import java.util.List;


@Service
public class UserDAO {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public AppUser findByUserId(long id) {
        return userRepository.findById(id);
    }

    public List<AppUser> findAllUsers() {
        return userRepository.findAll();
    }

    public void removeUser(AppUser user) {
        userRepository.delete(user);
    }


    public void createUser(AppUser user) {
        userRepository.save(user);
    }

    public boolean isExist(long id) {
        AppUser user = findByUserId(id);
        return user != null;
    }
}