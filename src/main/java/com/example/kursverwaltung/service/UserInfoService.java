/*
package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.UserInfo;
import com.example.kursverwaltung.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public void save(UserInfo user){
        userInfoRepository.save(user);
    }
    public UserInfo get(long id){
       return userInfoRepository.findById(id).get();
    }
    public void delete(long id){
        userInfoRepository.deleteById(id);
    }
    public List<UserInfo> listAll() {
        return userInfoRepository.findAll();
    }

}
*/
