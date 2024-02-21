package org.example;

import java.util.ResourceBundle;

public class SudokuException extends Exception {

    public SudokuException(String messageKey, Throwable cause) {
        super(ResourceBundle.getBundle(
                "ExceptionMessages", LocaleManager.getCurrentLocale()).getString(messageKey), cause);
    }
}
