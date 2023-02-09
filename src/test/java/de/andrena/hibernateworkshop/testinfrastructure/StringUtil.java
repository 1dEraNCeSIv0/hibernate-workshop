package de.andrena.hibernateworkshop.testinfrastructure;

import java.util.Random;

public final class StringUtil {

    private StringUtil() {}

    public static String withRandomSuffix(String string) {
        return String.format("%s %s", string, randomAlphabetic(8));
    }

    private static String randomAlphabetic(int length) {
        return new Random().ints('A', 'z')
                .filter(StringUtil::isAlphabetic)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static boolean isAlphabetic(int character) {
        return character <= 'Z' || 'a' <= character;
    }

}