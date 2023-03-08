package com.example.kursverwaltung.service;

import com.example.kursverwaltung.config.UserInfoUserDetails;
import com.example.kursverwaltung.domain.UserInfo;
import com.example.kursverwaltung.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//			System.out.println(encoder.encode("root"));
    @Autowired
    public void DataLoader(UserInfoRepository userRepository) {
        this.repository = userRepository;
        LoadUsers();
    }

    private void LoadUsers() {
        repository.save(new UserInfo(1,"boot", passwordEncoder.encode("boot"), "ADMIN"));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByUsername(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found! " + username));
    }


    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system";
    }

    public List<UserInfo> listAll() {
        return repository.findAll();
    }

    public void save(UserInfo userInfo) {
        repository.save(userInfo);
    }

    public Optional<UserInfo> get(int id) {
        return Optional.of(repository.findById(id).get());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public Optional<UserInfo> getUserId(int id) {
        return repository.findById(id);
    }

    public List<UserInfo> getUserList() {
        return repository.findAll();
    }

    public List<UserInfo> findByKeyword(String keyword) {
        return repository.findByKeyword(keyword);
    }
}
