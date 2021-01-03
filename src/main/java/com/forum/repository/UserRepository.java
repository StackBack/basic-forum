package com.forum.repository;

import com.forum.domain.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    @Query("SELECT u from CustomUser u where u.age >= :age")
    List<CustomUser> findUser(@Param("age") Integer age);

    @Query("SELECT a.user from Article a where a.color = :color")
    List<CustomUser> findArticleByColor(@Param("color") String color);

    @Query("select a.user from Article a group by a.user having count (a.id) > 3")
    List<CustomUser> findArticleByCount();

    Optional<CustomUser> findByName(@Param("name") String name);
}
