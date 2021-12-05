package com.osstem.notice.repository;

import com.osstem.notice.domain.board.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // fetch join
    @Query("select c from Comment c left join fetch c.notice")
    List<Comment> findCommentFetchJoin();

    // EntityGraph outerjoin
    @Override
    @EntityGraph(attributePaths = {"notice"})
    List<Comment> findAll();

    // EntityGraph outerjoin
//    @Override
//    @EntityGraph("Notice.all")
//    List<Comment> findAll();
}