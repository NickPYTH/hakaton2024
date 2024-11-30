package de401t.service;

import de401t.model.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramService {

    public void sendNotify(Request request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("role", "client");
        formData.add("tg_id", request.getClient().getTgId());
        formData.add("request_id", String.valueOf(request.getId()));
        formData.add("request_name", request.getName());
        formData.add("link", "test");
        formData.add("create_date", request.getCreateDate().toString());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

        restTemplate.postForObject("https://evgeny1337-hackatonbot-c635.twc1.net/sendnotif", requestEntity, String.class);
    }

}
