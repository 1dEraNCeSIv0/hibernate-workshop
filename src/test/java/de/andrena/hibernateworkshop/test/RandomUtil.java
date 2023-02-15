package de.andrena.hibernateworkshop.test;

import java.util.Random;

public final class RandomUtil {

    private static final int ASCII_START = 0;
    private static final int ASCII_END = 128;

    private RandomUtil() {}

    public static String withRandomSuffix(String string) {
        return String.format("%s %s", string, randomAlphabetic(8));
    }

    public static int randomInt() {
        return new Random().nextInt();
    }

    private static String randomAlphabetic(int length) {
        return new Random().ints(ASCII_START, ASCII_END)
                .filter(RandomUtil::isAlphabetic)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static boolean isAlphabetic(int character) {
        return ('A' <= character && character <= 'Z') || ('a' <= character && character <= 'z');
    }

}