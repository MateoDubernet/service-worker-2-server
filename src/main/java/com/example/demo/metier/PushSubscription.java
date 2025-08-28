package com.example.demo.metier;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PushSubscription {
    private String endpoint;

    private Map<String, String> keys;
}
