package org.example;

import org.junit.jupiter.api.Test;

import java.util.Locale;

public class LocaleManagerTest {

    @Test
    public void testGetCurrentLocale() {
        LocaleManager localeManager = new LocaleManager();
        Locale locale = localeManager.getCurrentLocale();
        assert(locale.getLanguage().equals("pl"));
    }
}
