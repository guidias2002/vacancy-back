package com.gcd.vacancy.service;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordGenerator {

    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%&*";
    private static final String ALL_CHARACTERS = UPPERCASE_LETTERS + LOWERCASE_LETTERS + NUMBERS + SPECIAL_CHARACTERS;

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generatePassword(int length) {
        if (length < 6) {
            throw new IllegalArgumentException("Password length must be at least 6 characters");
        }

        StringBuilder password = new StringBuilder();
        password.append(randomCharacter(UPPERCASE_LETTERS));
        password.append(randomCharacter(LOWERCASE_LETTERS));
        password.append(randomCharacter(NUMBERS));
        password.append(randomCharacter(SPECIAL_CHARACTERS));

        IntStream.range(0, length - 4)
                .mapToObj(i -> randomCharacter(ALL_CHARACTERS))
                .forEach(password::append);

        return shuffleString(password.toString());
    }

    private static char randomCharacter(String characters) {
        return characters.charAt(RANDOM.nextInt(characters.length()));
    }

    private static String shuffleString(String input) {
        return IntStream.range(0, input.length())
                .mapToObj(i -> input.charAt(RANDOM.nextInt(input.length())))
                .collect(Collectors.toList())
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
