package com.example.user.helper;

import java.security.SecureRandom;

public class Utils {

    private static final SecureRandom random = new SecureRandom();

    public static Integer generateCode(int from, int to) {
        return random.nextInt(from, to);
    }


}
