package com.springshowcase.examplewebhook;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
@EnableConfigurationProperties(WhatsappConfigProperties.class)
public class WhatsappWebhook {
    private WhatsappConfigProperties waProperties;

    public WhatsappWebhook(WhatsappConfigProperties props) {
        this.waProperties = props;
    }

    @GetMapping
    ResponseEntity<String> webHookVerify(@RequestParam("hub.mode") String mode,
            @RequestParam("hub.challenge") String challenge,
            @RequestParam("hub.verify_token") String token) {
        System.out.println(mode);
        System.out.println(challenge);
        System.out.println(token);
        if (mode.equals("subscribe") && token.equals(this.waProperties.appToken())) {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Some Mismatch with app token", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping
    public void getRequest(@RequestBody String json) {
        System.out.println(json);
    }
}