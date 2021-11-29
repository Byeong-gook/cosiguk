package com.coronacommunity.CoronaCommunity.repository;


import com.coronacommunity.CoronaCommunity.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
/*
    @Query(value = "SELECT b.COUNT(*) FROM Board b WHERE b.view = 0", nativeQuery = true)
    Collection<Board> findTotalCount();
    */
}
