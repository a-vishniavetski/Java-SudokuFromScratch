package com.example.viewproject;

import java.util.ListResourceBundle;

public class AuthorsResourceBundle_pl extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"authors", new String[] {"Hubert Klonowski", "Aliaksei Vishniavetski"}},
                {"authorLabel", "[PL]Autorzy"}
        };
    }
}
