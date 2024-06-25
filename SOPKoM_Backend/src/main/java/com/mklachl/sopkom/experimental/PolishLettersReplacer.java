package com.mklachl.sopkom.experimental;

import java.util.HashMap;
import java.util.Map;

public class PolishLettersReplacer {

    private static final Map<Character, Character> polishToNonAccentedMap = new HashMap<>();

    static {
        polishToNonAccentedMap.put('ą', 'a');
        polishToNonAccentedMap.put('ć', 'c');
        polishToNonAccentedMap.put('ę', 'e');
        polishToNonAccentedMap.put('ł', 'l');
        polishToNonAccentedMap.put('ń', 'n');
        polishToNonAccentedMap.put('ó', 'o');
        polishToNonAccentedMap.put('ś', 's');
        polishToNonAccentedMap.put('ź', 'z');
        polishToNonAccentedMap.put('ż', 'z');
        polishToNonAccentedMap.put('Ą', 'A');
        polishToNonAccentedMap.put('Ć', 'C');
        polishToNonAccentedMap.put('Ę', 'E');
        polishToNonAccentedMap.put('Ł', 'L');
        polishToNonAccentedMap.put('Ń', 'N');
        polishToNonAccentedMap.put('Ó', 'O');
        polishToNonAccentedMap.put('Ś', 'S');
        polishToNonAccentedMap.put('Ź', 'Z');
        polishToNonAccentedMap.put('Ż', 'Z');
    }

    public static String replacePolishLetters(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder result = new StringBuilder(input.length());

        for (char c : input.toCharArray()) {
            Character replacement = polishToNonAccentedMap.get(c);
            if (replacement != null) {
                result.append(replacement);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    
}