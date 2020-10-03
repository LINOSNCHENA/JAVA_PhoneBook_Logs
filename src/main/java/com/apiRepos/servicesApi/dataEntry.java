package com.apiRepos.servicesApi;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.apiRepos.contactDaoImplemented;
import com.apiRepos.contactsDao;
import org.apache.log4j.Logger;

public class dataEntry {

    contactsDao phoneInserter = new contactDaoImplemented();
    final static Logger logger = Logger.getLogger(dataEntry.class);

    public static boolean isValidFormat(String mobile) {
        Pattern numberDesign = Pattern.compile("^[8/9]?[5-7][0-9]{5}");
        Matcher matchResults = numberDesign.matcher(mobile);
        return (matchResults.find() && matchResults.group().equals(mobile));
    }

    public void insertData() {
        final int MAX_LENGTH = 8;
        String Name = "unknown";
        int Mobile = 5;
        int Office = 5;
        int Stars = 1;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the new contact : ");
        scanner: while (scanner.hasNext()) {
            if (scanner.hasNextLine()) {
                Name = scanner.nextLine();

                if (Character.isLetter(Name.charAt(0)) && String.valueOf(Name).length() <= MAX_LENGTH) {
                    break scanner;
                } else {
                    if (!Character.isLetter(Name.charAt(0))) {
                        logger.warn("Error: First character must be a letter");
                        System.out.println("Error: First character must be a letter");
                        System.out.print("Please, enter the contact name number again : ");
                    } else if (String.valueOf(Name).length() > MAX_LENGTH) {
                        logger.warn("ERROR: Input contact name was too long");
                        System.out.println("ERROR: Input too long at " + String.valueOf(Name).length() + " characters");
                        System.out.print("Please, enter the contact name number again : ");
                    }
                }
            } else {
                logger.error("ERROR: Invalid Input, Only alpabetical characters allowed");
                System.out.println("ERROR: Invalid Input, Only alpabetical characters allowed");
                System.out.print("Please, enter the contact name number again : ");
                scanner.next();
            }
        }

        System.out.print("Enter the Mobile number of new contact : ");
        scanner: while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                Mobile = scanner.nextInt();
                String Mobile2 = Integer.toString(Mobile);
                if (isValidFormat(Mobile2)) {
                    break scanner;
                } else {
                    logger.error("ERROR: Input mobile number was too long");
                    System.out.println("ERROR: Input not correct format (e.g.(87)12345) at the "
                            + String.valueOf(Mobile2).length() + "th characters");
                    System.out.print("Please, enter the Mobile phone number again : ");
                }
            } else {
                logger.error("ERROR: Invalid Input. Please, enter int digits only");
                System.out.println("ERROR: Invalid Input. Please, enter int digits only");
                System.out.print("Please, enter the Mobile phone number again : ");
                scanner.next();
            }
        }
        System.out.print("Enter office number of new contact : ");
        scanner: while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                Office = scanner.nextInt();
                String Office2 = Integer.toString(Office);
                if (isValidFormat(Office2)) {
                    break scanner;
                } else {
                    logger.error("ERROR: Input office number was too long");
                    System.out.println("ERROR: Input not correct format (e.g.(87)12345) at the "
                            + String.valueOf(Office2).length() + "th characters");
                    System.out.print("Please, enter the Office phone number again : ");
                }
            } else {
                logger.error("ERROR: Invalid Input. Please, enter int digits only");
                System.out.println("ERROR: Invalid Input. Please, enter int digits only");
                System.out.print("Please, enter the Mobile phone number again : ");
                scanner.next();
            }
        }

        phoneInserter.insertNewContact(Name.toUpperCase(), Mobile, Office, Stars);
        System.out.println(
                "\n ==================================== DATA ENTRY IS COMPLETE ======================================\n");
    }

}
