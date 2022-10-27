package com.mispi.mispibot.service.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mispi.mispibot.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
}
