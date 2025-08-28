package com.example.demo.metier.repo;

import com.example.demo.metier.PushSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PushSubscriptionRepo extends JpaRepository<PushSubscriptionEntity, Long> {
}
