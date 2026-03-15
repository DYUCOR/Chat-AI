package com.chat.chat.repository;

import com.chat.chat.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMassageRepository extends JpaRepository<ChatMessage,Integer> {
}
