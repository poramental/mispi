package com.mispi.mispibot.service.DAO;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.mispi.mispibot.models.Mispi;
import com.mispi.mispibot.service.DAO.Repositories.MispiRepository;

import java.util.List;

@Service
public class MispiDAO {

    private final MispiRepository mispiRepository;

    @Autowired
    public MispiDAO(MispiRepository mispiRepository) {
        this.mispiRepository = mispiRepository;
    }

    public Mispi findByMispiId(long id) {
        return mispiRepository.findById(id);
    }

    public List<Mispi> findAllUsers() {
        return mispiRepository.findAll();
    }

    public void removeMispi(Mispi mispi) {
        mispiRepository.delete(mispi);
    }


    public void save(Mispi user) {
        mispiRepository.save(user);
    }

    public boolean isExist(long id) {
        Mispi user = findByMispiId(id);
        return user != null;
    }
}