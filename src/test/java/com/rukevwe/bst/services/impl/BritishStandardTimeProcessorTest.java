package com.rukevwe.bst.services.impl;

import com.rukevwe.bst.exceptions.InvalidInputException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BritishStandardTimeProcessorTest {

    private static BritishStandardTimeProcessor britishStandardTimeProcessor;

    @BeforeAll
    public static void setUp() {

        britishStandardTimeProcessor = new BritishStandardTimeProcessor();
    }

    @Test
    public void processInput_whenInputIsInvalid_throwsInvalidInputExceptionWithExpectedMessage() {

        // Given
        final String invalidTime = "09:000";

        // When
        final ThrowableAssert.ThrowingCallable result = () -> britishStandardTimeProcessor.processInput(invalidTime);

        // Then
        assertThatThrownBy(result)
                .isExactlyInstanceOf(InvalidInputException.class)
                .hasMessageContaining("The accepted input string pattern is dd:dd or d:dd where d is a single digit. Please check input.");
    }

    @Test
    public void processInput_whenInputIsValid_returnsExpectedString() {

        // Given
        final String validTime = "1:00";
        final String validTime1 = "2:05";
        final String validTime2 = "4:15";
        final String validTime3 = "6:25";
        final String validTime4 = "6:32";
        final String validTime5 = "7:30";
        final String validTime6 = "7:35";
        final String validTime7 = "8:40";
        final String validTime8 = "9:45";
        final String validTime9 = "11:55";
        final String validTime10 = "00:00";
        final String validTime11 = "12:00";
        final String validTime12 = "14:00";

        // When
        final String result = britishStandardTimeProcessor.processInput(validTime);
        final String result1 = britishStandardTimeProcessor.processInput(validTime1);
        final String result2 = britishStandardTimeProcessor.processInput(validTime2);
        final String result3 = britishStandardTimeProcessor.processInput(validTime3);
        final String result4 = britishStandardTimeProcessor.processInput(validTime4);
        final String result5 = britishStandardTimeProcessor.processInput(validTime5);
        final String result6 = britishStandardTimeProcessor.processInput(validTime6);
        final String result7 = britishStandardTimeProcessor.processInput(validTime7);
        final String result8 = britishStandardTimeProcessor.processInput(validTime8);
        final String result9 = britishStandardTimeProcessor.processInput(validTime9);
        final String result10 = britishStandardTimeProcessor.processInput(validTime10);
        final String result11 = britishStandardTimeProcessor.processInput(validTime11);
        final String result12 = britishStandardTimeProcessor.processInput(validTime12);

        // Then
        assertEquals(result, "one o'clock");
        assertEquals(result1, "five past two");
        assertEquals(result2, "quarter past four");
        assertEquals(result3, "twenty-five past six");
        assertEquals(result4, "six thirty-two");
        assertEquals(result5, "half past seven");
        assertEquals(result6, "twenty-five to eight");
        assertEquals(result7, "twenty to nine");
        assertEquals(result8, "quarter to ten");
        assertEquals(result9, "five to noon");
        assertEquals(result10, "midnight");
        assertEquals(result11, "noon");
        assertEquals(result12, "two o'clock");
    }
}
