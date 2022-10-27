package com.mispi.mispibot.service.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mispi.mispibot.models.Mispi;


public interface MispiRepository extends JpaRepository<Mispi,Long> {
    Mispi findById(long id);
}
