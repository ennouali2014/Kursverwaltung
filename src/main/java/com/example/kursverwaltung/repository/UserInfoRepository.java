package com.example.kursverwaltung.repository;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo>findByUsername(String username);

    @Query(value = "select * from user u where u.username like %:keyword% ",nativeQuery = true)
    List<UserInfo> findByKeyword(@Param("keyword") String keyword);
}
