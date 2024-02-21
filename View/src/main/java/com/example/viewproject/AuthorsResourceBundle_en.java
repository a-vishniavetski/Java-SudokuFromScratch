package com.example.viewproject;

import java.util.ListResourceBundle;

public class AuthorsResourceBundle_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"authors", new String[] {"Hubert Klonowski", "Aliaksei Vishniavetski"}},
                {"authorLabel", "[EN]Authors"}
        };
    }
}
