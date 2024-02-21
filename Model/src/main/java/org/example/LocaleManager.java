package org.example;

import java.util.Locale;

public class LocaleManager {
    private static Locale currentLocale = new Locale("pl", "PL");

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

}