package com.example.demo.metier;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity()
@Table(name = "push_subscription", schema = "public")
public class PushSubscriptionEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private String p256dhKey;

    @Column(nullable = false)
    private String authKey;
}
