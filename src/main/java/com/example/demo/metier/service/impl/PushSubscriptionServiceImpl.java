package com.example.demo.metier.service.impl;

import com.example.demo.metier.PushSubscription;
import com.example.demo.metier.PushSubscriptionEntity;
import com.example.demo.metier.repo.PushSubscriptionRepo;
import com.example.demo.metier.service.PushSubscriptionService;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PushSubscriptionServiceImpl implements PushSubscriptionService {

    private static final PushService pushService = new PushService();
    private final PushSubscriptionRepo pushSubscriptionRepo;

    public PushSubscriptionServiceImpl(PushSubscriptionRepo pushSubscriptionRepo) {
        this.pushSubscriptionRepo = pushSubscriptionRepo;
    }

    @Override
    public void sendNotification(String message) throws GeneralSecurityException, JoseException, IOException, ExecutionException, InterruptedException {

        pushService.setPublicKey("BKksUBFA_ZzP2iWkYB1kD3UcTa6PPr51J9uAGI0F4xikSgWkB60CUcVMiNldAoGFdHzylIkGBNILxcyXhERJmKQ");
        pushService.setPrivateKey("Lwxt5BisuKRwR73VrTtZZeU2NpG9-BIBgoNFXGDikXw");

        String payload = "{ " +
                "\"notification\": { " +
                "\"title\": \"Nouvelle Notification\", " +
                "\"body\": \"Vous avez un nouveau message.\" " +
                "}"+
                "}";

        List<PushSubscriptionEntity> pushSubscriptions = pushSubscriptionRepo.findAll();

        for (PushSubscriptionEntity pushSubscription: pushSubscriptions) {
            Notification notification = new Notification(
                    pushSubscription.getEndpoint(),
                    pushSubscription.getP256dhKey(),
                    pushSubscription.getAuthKey(),
                    payload
            );

            pushService.send(notification);
        }
    }

    @Override
    public void saveSubscription(PushSubscription subscription) {
        PushSubscriptionEntity pushSubscriptionEntity = new PushSubscriptionEntity();
        pushSubscriptionEntity.setEndpoint(subscription.getEndpoint());
        pushSubscriptionEntity.setP256dhKey(subscription.getKeys().get("p256dh"));
        pushSubscriptionEntity.setAuthKey(subscription.getKeys().get("auth"));

        pushSubscriptionRepo.save(pushSubscriptionEntity);
    }
}
