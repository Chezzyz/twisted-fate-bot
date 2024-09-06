package ru.readysetcock.fate_telegram_bot.controllers;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TestController {

    @Value("${chatgpt.api.key}")
    public String gptApiKey;

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testGpt(@RequestBody Map<String, String> prompt) {
        OpenAiService openAiService = new OpenAiService(gptApiKey);

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .temperature(0.9)
                .n(1)
                .model("gpt-4o-mini")
                .maxTokens(1024)
                .stream(false)
                .messages(List.of(new ChatMessage("user", prompt.get("text"))))
                .build();

        Map<String, Object> result = openAiService.createChatCompletion(request).getChoices().stream()
                .collect(Collectors.toMap(choice -> choice.getIndex().toString(), choice -> choice));

        return ResponseEntity.ok(result);
    }
}
