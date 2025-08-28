package com.example.demo.controller;

import com.example.demo.metier.PushSubscription;
import com.example.demo.metier.service.PushSubscriptionService;
import org.jose4j.lang.JoseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/service-worker/push-subscription")
@CrossOrigin("http://127.0.0.1:8080/")
public class PushSubscriptionController {

    private final PushSubscriptionService pushSubscriptionService;

    public PushSubscriptionController(PushSubscriptionService pushSubscriptionService) {
        this.pushSubscriptionService = pushSubscriptionService;
    }

    @PostMapping("/save-subscription")
    public void saveSubscription(@RequestBody PushSubscription subscription) {
        pushSubscriptionService.saveSubscription(subscription);
    }

    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody String message) throws JoseException, GeneralSecurityException, IOException, ExecutionException, InterruptedException {
        pushSubscriptionService.sendNotification(message);
    }
}
