package com.apiRepos.servicesApi;

import java.util.Scanner;

import com.apiRepos.contactDaoImplemented;
import com.apiRepos.contactsDao;

import org.apache.log4j.Logger;

public class dataEntry {
    final static Logger logger = Logger.getLogger(dataEntry.class);

    //////////////////////////////////////////////////////////////////////////////// NAME
    public void insertData() {
        final int MAX_LENGTH = 20;
        int pnumber1 = 0;
        int pnumber2 = 0;
        String pname = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an INSERT name of the contact : ");
        scanner: while (scanner.hasNext()) {
            if (scanner.hasNextLine()) {
                pname = scanner.nextLine();
                if (String.valueOf(pname).length() <= MAX_LENGTH) {
                    break scanner;
                } else {

                    logger.warn("ERROR: Input contact name was too long");
                    System.out.print("Enter a name of the contact : ");
                }
            } else {
                logger.error("ERROR: Invalid Input, Only alpabetical characters allowed");
                System.out.print("Enter a name of the contact : ");
                scanner.next();
            }
        }

        /////////////////////////////////////////////////// TWO NUMBERS
        System.out.print("Enter the INSERT MOBILE PHONE number: ");
        scanner: while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                pnumber1 = scanner.nextInt();
                if (String.valueOf(pnumber1).length() <= MAX_LENGTH) {
                    break scanner;
                } else {
                    logger.error("ERROR: Input MOBILE number was too long");
                    System.out.println(
                            "ERROR: Input MOBILE number was too long at" + String.valueOf(pnumber2) + "characters");
                    System.out.print("Please, enter the MOBILE PHONE number again : ");
                }
            } else {
                logger.error("ERROR: Invalid Input. Please, enter only number");
                System.out.print("Please, enter the MOBILE PHONE number again : ");
                scanner.next();
            }
        }
        System.out.print("Enter the INSERT OFFICE PHONE number: ");
        scanner: while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                pnumber2 = scanner.nextInt();
                if (String.valueOf(pnumber2).length() <= MAX_LENGTH) {
                    break scanner;
                } else {
                    logger.error("ERROR: Input OFFICE number was too long");
                    System.out.println(
                            "ERROR: Input OFFICE number was too long at" + String.valueOf(pnumber2) + "characters");
                    System.out.print("Please, enter the OFFICE PHONE number again : ");
                }
            } else {
                logger.error("ERROR: Invalid Input. Please, enter only number");
                System.out.print("Please, enter the OFFICE PHONE number again : ");
                scanner.next();
            }
        }
        contactsDao phoneDefaults = new contactDaoImplemented();
        phoneDefaults.insertNewContact(pname.toUpperCase(), pnumber1, pnumber2);
        System.out.println("\n !======================= DATA ENTRY #2 COMPLETE ===========================!\n");
        ////////////////////////////////////////////////////////////////// TWO NUMBERS
    }
}
