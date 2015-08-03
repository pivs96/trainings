package com.exadel.repository;

import com.exadel.dto.UserDTO;
import com.exadel.model.entity.user.User;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    Optional<User> findOneByEmail(String email);

  //  public Page<User> findBySyncJobIdOrderByLastUpdateDesc(long syncJobId, Pageable p);
   /* @Query("select a.user_id from authentification a where a.login = ?1")
    Optional<Long> findIdByLogin(String login);*/
}