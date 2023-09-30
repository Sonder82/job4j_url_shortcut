package ru.job4j.urlshortcut.utils;

import java.security.SecureRandom;

public class CodeGenerator {

    private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();

    private static final String DIGIT = "0123456789";

    private static final int CODE_LENGTH = 8;

    private static final String CODE_ALLOW = CHAR_LOWERCASE + CHAR_UPPERCASE + DIGIT;

    private static SecureRandom random = new SecureRandom();


    public static String getCode() {
        StringBuilder result = new StringBuilder(CODE_LENGTH);
        String code = generateRandomString();
        return result.append(code).toString();
    }

    private static String generateRandomString() {
        StringBuilder result = new StringBuilder(CodeGenerator.CODE_LENGTH);
        for (int i = 0; i < CodeGenerator.CODE_LENGTH; i++) {
            int index = random.nextInt(CodeGenerator.CODE_LENGTH);
            result.append(CodeGenerator.CODE_ALLOW.charAt(index));
        }
        return result.toString();
    }
}
