package com.rukevwe.bst;

import com.rukevwe.bst.validators.impl.InputTimeStringValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class BritishStandardTimeApplication {

    private static final Logger logger = Logger.getLogger(BritishStandardTimeApplication.class.getName());

    public static void main(String[] args) {

        logger.log(Level.INFO, "Start processing");

        try {
            if (args.length != 1) {
                logger.log(Level.INFO, "invalid number of arguments");
                return;
            }

            String inputTime = args[0];

            InputTimeStringValidator inputTimeStringValidator = new InputTimeStringValidator();

            inputTimeStringValidator.validate(inputTime);

            logger.log(Level.INFO, "Finished processing");
        } catch (Exception ex) {

            logger.log(Level.SEVERE, "Encountered error while processing", ex);
        }
    }
}
