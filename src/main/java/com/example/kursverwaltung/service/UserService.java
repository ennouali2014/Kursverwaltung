package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.User;
import com.example.kursverwaltung.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public void save(User user){
        repo.save(user);
    }
    public User get(long id){
       return repo.findById(id).get();
    }
    public void delete(long id){
        repo.deleteById(id);
    }
    public List<User> listAll() {
        return repo.findAll();
    }

}
