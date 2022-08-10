package com.rukevwe.bst.validators.impl;

import com.rukevwe.bst.exceptions.InvalidInputException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputTimeStringValidatorTest {

    private static InputTimeStringValidator inputTimeStringValidator;

    @BeforeAll
    public static void setUp() {

        inputTimeStringValidator = new InputTimeStringValidator();
    }

    @Test
    public void validate_whenInputPatternIsInvalid_throwsInvalidInputExceptionWithExpectedMessage() {

        // Given
        final String invalidTime = "09:000";

        // When
        final ThrowableAssert.ThrowingCallable result = () -> inputTimeStringValidator.validate(invalidTime);

        // Then
        assertThatThrownBy(result)
                .isExactlyInstanceOf(InvalidInputException.class)
                .hasMessageContaining("The accepted input string pattern is dd:dd or d:dd where d is a single digit. Please check input.");
    }

    @Test
    public void validate_whenNumberOfHoursIsInvalid_throwsInvalidInputExceptionWithExpectedMessage() {

        // Given
        final String invalidTime1 = "25:00";
        final String invalidTime2 = "24:00";


        // When
        final ThrowableAssert.ThrowingCallable result1 = () -> inputTimeStringValidator.validate(invalidTime1);
        final ThrowableAssert.ThrowingCallable result2 = () -> inputTimeStringValidator.validate(invalidTime2);

        // Then
        assertThatThrownBy(result1)
                .isExactlyInstanceOf(InvalidInputException.class)
                .hasMessageContaining("The number of hours must be between 0 and 23. Please check input.");
        assertThatThrownBy(result2)
                .isExactlyInstanceOf(InvalidInputException.class)
                .hasMessageContaining("The number of hours must be between 0 and 23. Please check input.");
    }

    @Test
    public void validate_whenNumberOfMinutesIsInvalid_throwsInvalidInputExceptionWithExpectedMessage() {

        // Given
        final String invalidTime1 = "12:70";
        final String invalidTime2 = "12:80";


        // When
        final ThrowableAssert.ThrowingCallable result1 = () -> inputTimeStringValidator.validate(invalidTime1);
        final ThrowableAssert.ThrowingCallable result2 = () -> inputTimeStringValidator.validate(invalidTime2);

        // Then
        assertThatThrownBy(result1)
                .isExactlyInstanceOf(InvalidInputException.class)
                .hasMessageContaining("The number of minutes must be between 0 and 60. Please check input.");
        assertThatThrownBy(result2)
                .isExactlyInstanceOf(InvalidInputException.class)
                .hasMessageContaining("The number of minutes must be between 0 and 60. Please check input.");
    }

    @Test
    public void validate_whenNumberOfMinutesValid_throwsNoException() {

        // Given
        final String invalidTime1 = "12:50";
        final String invalidTime2 = "1:30";


        // When
        final ThrowableAssert.ThrowingCallable result1 = () -> inputTimeStringValidator.validate(invalidTime1);
        final ThrowableAssert.ThrowingCallable result2 = () -> inputTimeStringValidator.validate(invalidTime2);

        // Then
        assertThatNoException().isThrownBy(result1);
        assertThatNoException().isThrownBy(result2);
    }
}
