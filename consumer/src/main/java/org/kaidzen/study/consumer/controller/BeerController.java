package org.kaidzen.study.consumer.controller;

import org.kaidzen.study.consumer.domain.Person;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@RestController
public class BeerController {

    private final RestTemplate template;

    public BeerController() {
        this.template = new RestTemplate();
    }

    @PostMapping("/beer")
    public String gimmeBeer(@RequestBody Person person) {
        ResponseEntity<String> responseEntity = template.exchange(RequestEntity.post(URI.create("http://localhost:8090/check"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(person), String.class);
        String body = responseEntity.getBody();
        if (Objects.nonNull(body)) {
            return "OK".equals(body) ? "THERE YOU GO" : "GET LOST";
        }
        return "FAILED";
    }
}
