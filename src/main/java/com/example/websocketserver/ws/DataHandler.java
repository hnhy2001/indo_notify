package com.example.websocketserver.ws;

import com.example.websocketserver.model.BaseResponse;
import com.example.websocketserver.model.Notification;
import com.example.websocketserver.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.AbstractWebSocketMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class DataHandler extends TextWebSocketHandler {
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = (String) message.getPayload();
        Notification notification = objectMapper.readValue(json, Notification.class);
        notification.setCreateDate(DateUtil.getCurrenDateHour());
        notification.setUpdateDate(DateUtil.getCurrenDateHour());
        String url = "http://103.56.163.179:8080/api/v1/notification";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Notification> request = new HttpEntity<>(notification, headers);
        Notification response  = restTemplate.postForObject(url, request, Notification.class);
        String result = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(result));
    }
}
