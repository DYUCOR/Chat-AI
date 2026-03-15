package com.chat.chat.service;

import com.chat.chat.domain.ChatMessage;
import com.chat.chat.repository.ChatMassageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMassageRepository chatMassageRepository;

    public void saveMessage(ChatMessage message) {
        chatMassageRepository.save(message);

    }
}
