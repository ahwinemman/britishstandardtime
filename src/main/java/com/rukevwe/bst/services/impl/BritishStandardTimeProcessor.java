package com.rukevwe.bst.services.impl;

import com.rukevwe.bst.services.TimeProcessor;
import com.rukevwe.bst.validators.impl.InputTimeStringValidator;
import pl.allegro.finance.tradukisto.ValueConverters;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BritishStandardTimeProcessor implements TimeProcessor {

    private static final ValueConverters INT_CONVERTER = ValueConverters.ENGLISH_INTEGER;

    private static final InputTimeStringValidator INPUT_TIME_STRING_VALIDATOR = new InputTimeStringValidator();

    private static final int HOUR_FLAG = 60;

    private static final int HALF_HOUR_FLAG = 30;

    private static final int QUARTER_PAST_FLAG = 15;

    private static final int QUARTER_TO_FLAG = 45;

    private static final int ZERO = 0;

    private static final int TWELVE = 12;

    private static final Logger logger = Logger.getLogger(BritishStandardTimeProcessor.class.getName());

    private static final Map<Integer, String> BRITISH_DEFAULT_HOUR_IN_WORDS_MAP = Map.ofEntries(
            Map.entry(12, "noon"),
            Map.entry(24, "midnight"),
            Map.entry(00, "midnight")
    );

    @Override
    public String processInput(String inputTime) {

        INPUT_TIME_STRING_VALIDATOR.validate(inputTime);

        String[] hourAndMinutesArray = getHoursAndMinutes(inputTime);

        int hours = Integer.valueOf(hourAndMinutesArray[0]);
        int minutes = Integer.valueOf(hourAndMinutesArray[1]);

        String timeInString = processTime(hours, minutes);
        logger.log(Level.INFO, "The time is: " + timeInString);

        return processTime(hours, minutes);
    }

    private String processTime(int hours, int minutes) {

        if (hours == ZERO && minutes == ZERO) {
            return "midnight";
        }

        if (hours == TWELVE && minutes == ZERO) {
            return "noon";
        }

        if (minutes == ZERO) {
            return INT_CONVERTER.asWords(hours % 12) + " o'clock";
        }

        if (minutes == HALF_HOUR_FLAG) {
            return processTimeAtHalfHour(hours, minutes);
        }

        if (minutes == QUARTER_PAST_FLAG) {
            return processTimeAtQuarterHour(hours, minutes);
        }

        if (minutes == QUARTER_TO_FLAG) {
            return processTimeAtQuarterTo(hours, minutes);
        }

        if (!ifNumberOfMinutesIsInBetween30And60(minutes)) {
            return processTimeWhenNumberOfMinutesIsLessThan30(hours, minutes);
        }

        if (ifNumberOfMinutesIsAMultipleOf5(minutes) && ifNumberOfMinutesIsInBetween30And60(minutes)) {
            return processTimeWhenNumberOfMinutesIsAMultipleOf5AndIsInBetween30And60(hours, minutes);
        }

        return processTimeWhenNumberOfMinutesIsNotAMultipleOf5AndIsInBetween30And60(hours, minutes);
    }

    private String processTimeWhenNumberOfMinutesIsNotAMultipleOf5AndIsInBetween30And60(int hours, int minutes) {

        String hourInWords = BRITISH_DEFAULT_HOUR_IN_WORDS_MAP.get(hours);

        return hourInWords != null ? INT_CONVERTER.asWords(minutes) + " past " + hourInWords :
                INT_CONVERTER.asWords(hours % 12) + " " + INT_CONVERTER.asWords(minutes);
    }

    private String processTimeWhenNumberOfMinutesIsAMultipleOf5AndIsInBetween30And60(int hours, int minutes) {

        int leftInMinutesToHourFlag = HOUR_FLAG - minutes;
        String hourInWords = BRITISH_DEFAULT_HOUR_IN_WORDS_MAP.get(hours + 1);

        return hourInWords != null ? INT_CONVERTER.asWords(leftInMinutesToHourFlag) + " to " + hourInWords :
                INT_CONVERTER.asWords(leftInMinutesToHourFlag) + " to " + INT_CONVERTER.asWords(hours % 12 + 1);
    }

    private String processTimeWhenNumberOfMinutesIsLessThan30(int hours, int minutes) {

        String hourInWords = BRITISH_DEFAULT_HOUR_IN_WORDS_MAP.get(hours);

        return hourInWords != null ? INT_CONVERTER.asWords(minutes) + " past " + hourInWords :
                INT_CONVERTER.asWords(minutes) + " past " + INT_CONVERTER.asWords(hours % 12);
    }

    private String processTimeAtQuarterTo(int hours, int minutes) {

        String hourInWords = BRITISH_DEFAULT_HOUR_IN_WORDS_MAP.get(hours + 1);

        return hourInWords != null ? "quarter to " + hourInWords : "quarter to " + INT_CONVERTER.asWords(hours % 12 + 1);
    }

    private String processTimeAtHalfHour(int hours, int minutes) {

        String hourInWords = BRITISH_DEFAULT_HOUR_IN_WORDS_MAP.get(hours);

        return hourInWords != null ? "half past " + hourInWords : "half past " + INT_CONVERTER.asWords(hours % 12);
    }

    private String processTimeAtQuarterHour(int hours, int minutes) {

        String hourInWords = BRITISH_DEFAULT_HOUR_IN_WORDS_MAP.get(hours);

        return hourInWords != null ? "quarter past " + hourInWords : "quarter past " + INT_CONVERTER.asWords(hours % 12);
    }

    private boolean ifNumberOfMinutesIsAMultipleOf5(int minutes) {

        return (minutes % 5 == 0);
    }

    private boolean ifNumberOfMinutesIsInBetween30And60(int minutes) {

        return (minutes > 30 && minutes < 60);
    }

    private String[] getHoursAndMinutes(String inputTime) {

        return inputTime.split(":");
    }

    public static void main(String[] args) {

        BritishStandardTimeProcessor britishStandardTimeProcessor = new BritishStandardTimeProcessor();

        System.out.println(britishStandardTimeProcessor.processTime(1, 00));
        System.out.println(britishStandardTimeProcessor.processTime(2, 00));
        System.out.println(britishStandardTimeProcessor.processTime(2, 05));
        System.out.println(britishStandardTimeProcessor.processTime(5, 20));
        System.out.println(britishStandardTimeProcessor.processTime(9, 45));
        System.out.println(britishStandardTimeProcessor.processTime(8, 40));
        System.out.println(britishStandardTimeProcessor.processTime(17, 40));
        System.out.println(britishStandardTimeProcessor.processTime(00, 00));
        System.out.println(britishStandardTimeProcessor.processTime(12, 00));
        System.out.println(britishStandardTimeProcessor.processTime(12, 30));
        System.out.println(britishStandardTimeProcessor.processTime(00, 30));

        System.out.println(britishStandardTimeProcessor.processTime(17, 30));
        System.out.println(britishStandardTimeProcessor.processTime(17, 45));
        System.out.println(britishStandardTimeProcessor.processTime(6, 11));
        System.out.println(britishStandardTimeProcessor.processTime(6, 32));

        System.out.println(britishStandardTimeProcessor.processTime(00, 32));
        System.out.println(britishStandardTimeProcessor.processTime(12, 32));
        System.out.println(britishStandardTimeProcessor.processTime(12, 28));

        System.out.println(britishStandardTimeProcessor.processTime(11, 45));

    }
}
