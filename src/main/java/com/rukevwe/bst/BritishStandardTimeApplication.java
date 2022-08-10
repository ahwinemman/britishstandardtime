package com.rukevwe.bst;

import com.rukevwe.bst.exceptions.InvalidInputException;
import com.rukevwe.bst.services.impl.BritishStandardTimeProcessor;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BritishStandardTimeApplication {

    private static final Logger logger = Logger.getLogger(BritishStandardTimeApplication.class.getName());

    public static void main(String[] args) {

        logger.log(Level.INFO, "Program begins...");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the input time you want to convert to british spoken form: ");
            String input = scanner.nextLine();

            try {
                if (input == null) {
                    throw new InvalidInputException("You are allowed to pass only one argument");
                }

                BritishStandardTimeProcessor britishStandardTimeProcessor = new BritishStandardTimeProcessor();
                britishStandardTimeProcessor.processInput(input);
            } catch (Exception ex) {

                logger.log(Level.SEVERE, "Encountered error while processing", ex);
            }
        }

    }
}
