package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DifficultyTest {

        @Test
        public void getFieldsToEraseTest() {
            Difficulty difficulty = Difficulty.EASY;
            int result = difficulty.getFieldsToErase();
            assertEquals(5, result);

            difficulty = Difficulty.NORMAL;
            result = difficulty.getFieldsToErase();
            assertEquals(10, result);

            difficulty = Difficulty.HARD;
            result = difficulty.getFieldsToErase();
            assertEquals(15, result);
        }

        @Test
        public void toStringTest() {
            Difficulty difficulty = Difficulty.EASY;
            String result = difficulty.toString();
            assertEquals("Łatwy", result);

            difficulty = Difficulty.NORMAL;
            result = difficulty.toString();
            assertEquals("Normalny", result);

            difficulty = Difficulty.HARD;
            result = difficulty.toString();
            assertEquals("Trudny", result);
        }
}
