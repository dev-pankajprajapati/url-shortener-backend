package com.urlshortener.util;

import java.security.SecureRandom;

public class ShortCodeGenerator {

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom RANDOM =
            new SecureRandom();

    public static String generateCode(int length) {

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index =
                    RANDOM.nextInt(CHARACTERS.length());

            code.append(
                    CHARACTERS.charAt(index)
            );
        }

        return code.toString();
    }
}