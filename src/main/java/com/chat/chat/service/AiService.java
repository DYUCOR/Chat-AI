package com.chat.chat.service;


import com.chat.chat.domain.ChatMessage;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AiService {

    @Value("${chat.ai.api-key}")
    String apiKey;

    private final ChatMessageService chatMessageService;
    public String enviarMensagemIA(String messagem) {

        Client client = Client.builder()
                .apiKey(apiKey)
                .build();
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3-flash-preview",
                        messagem,
                        null);

        ChatMessage chatMessage = ChatMessage.builder()
                .message(messagem)
                .response(response.text())
                .build();
        chatMessageService.saveMessage(chatMessage);

        return response.text();
    }
}