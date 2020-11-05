package org.kaidzen.study.producer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProducerController {

    @PostMapping("/check")
    public ResponseEntity<Response> check(@RequestBody PersonToCheck personToCheck){

        return personToCheck.getAge() >= 20 ?
                ResponseEntity.status(200).body(new Response(BeerCheckStatus.OK)) :
                ResponseEntity.status(200).body(new Response(BeerCheckStatus.NOT_OK));
    }
}
