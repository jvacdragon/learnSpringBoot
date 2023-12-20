package com.restapi.webservices.restfulwebservice.jpa;

import com.restapi.webservices.restfulwebservice.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying
    @Query("delete from Post p where p.user.id=:userId and p.id=:id")
    void deleteByUserIdAndPostId(@Param("userId") long userId, @Param("id") long id);
}
