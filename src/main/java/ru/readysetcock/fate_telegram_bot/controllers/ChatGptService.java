package ru.readysetcock.fate_telegram_bot.controllers;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatGptService {

    @Value("${chatgpt.api.key}")
    public String gptApiKey;

    public List<ChatCompletionChoice> sendToGpt(@RequestBody Map<String, String> prompt) {
        OpenAiService openAiService = new OpenAiService(gptApiKey);
        List<ChatMessage> messages = new ArrayList<>();
        for (Map.Entry<String, String> entry : prompt.entrySet()) {
            messages.add(new ChatMessage(entry.getKey(), entry.getValue()));
        }
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .temperature(0.9)
                .n(1)
                .model("gpt-4o-mini")
                .maxTokens(1024)
                .stream(false)
                .messages(messages)
                .build();

        return new ArrayList<>(openAiService.createChatCompletion(request).getChoices());}
}
