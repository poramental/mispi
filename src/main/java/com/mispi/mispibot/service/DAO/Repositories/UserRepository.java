package com.mispi.mispibot.service.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mispi.mispibot.models.AppUser;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser findById(long id);
}
