package com.springshowcase.examplewebhook;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("whatsapp")
public record WhatsappConfigProperties(String apiToken, String appToken) {
}
