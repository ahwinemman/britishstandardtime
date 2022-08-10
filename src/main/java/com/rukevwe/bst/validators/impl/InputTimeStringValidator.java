package com.rukevwe.bst.validators.impl;

import com.rukevwe.bst.exceptions.InvalidInputException;
import com.rukevwe.bst.validators.InputValidator;

import java.util.regex.Pattern;

public class InputTimeStringValidator implements InputValidator<String> {

    // Pattern should match "dd:dd" or "d:dd" when d is integer
    private static final Pattern INPUT_TIME_PATTERN = Pattern.compile("[0-9]{1,2}:[0-9]{2}");

    private static final int MAX_MINUTE = 60;

    private static final int MIN_MINUTE = 0;

    private static final int MAX_HOUR = 23;

    private static final int MIN_HOUR = 0;

    @Override
    public void validate(String input) {

        checkIfPatternIsValid(input);

        String[] splitTimeString = input.split(":");

        String hours = splitTimeString[0];
        checkIfNumberOfHoursIsValid(hours);

        String minutes = splitTimeString[1];
        checkIfNumberOfMinutesIsValid(minutes);
    }

    private void checkIfPatternIsValid(String input) {

        if (!INPUT_TIME_PATTERN.matcher(input).matches()) {
            throw new InvalidInputException("The accepted input string pattern is dd:dd or d:dd where d is a single digit. Please check " +
                                                    "input.");
        }
    }

    private void checkIfNumberOfHoursIsValid(String input) {

        int inputInInt = Integer.valueOf(input);

        if (inputInInt < MIN_HOUR || inputInInt > MAX_HOUR){
            throw new InvalidInputException("The number of hours must be between 0 and 23. Please check input.");
        }
    }

    private void checkIfNumberOfMinutesIsValid(String input) {

        int inputInInt = Integer.valueOf(input);

        if (inputInInt < MIN_MINUTE || inputInInt > MAX_MINUTE){
            throw new InvalidInputException("The number of minutes must be between 0 and 60. Please check input.");
        }
    }

}
