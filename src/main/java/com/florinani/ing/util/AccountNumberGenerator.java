package com.florinani.ing.util;

import org.springframework.stereotype.Component;

@Component
public class AccountNumberGenerator {

    public String generate(Long userId) {

        StringBuilder sb = new StringBuilder();
        String currentTime = String.valueOf(System.currentTimeMillis());
        sb.append(currentTime);
        sb.append(userId);

        return sb.toString();
    }
}
