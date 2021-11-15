package com.coronacommunity.CoronaCommunity.repository;

import com.coronacommunity.CoronaCommunity.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}