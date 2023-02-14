package de.andrena.hibernateworkshop.test;

import java.util.Random;

public final class StringUtil {

    private static final int ASCII_START = 0;
    private static final int ASCII_END = 128;

    private StringUtil() {}

    public static String withRandomSuffix(String string) {
        return String.format("%s %s", string, randomAlphabetic(8));
    }

    private static String randomAlphabetic(int length) {
        return new Random().ints(ASCII_START, ASCII_END)
                .filter(StringUtil::isAlphabetic)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static boolean isAlphabetic(int character) {
        return ('A' <= character && character <= 'Z') || ('a' <= character && character <= 'z');
    }

}