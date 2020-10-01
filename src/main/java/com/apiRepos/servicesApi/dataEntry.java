package com.apiRepos.servicesApi;

import java.util.Scanner;
import com.apiRepos.contactDaoImplemented;
import com.apiRepos.contactsDao;
import org.apache.log4j.Logger;

public class dataEntry {
    final static Logger logger = Logger.getLogger(dataEntry.class);

    public void insertData() {
        final int MAX_LENGTH = 20;
        int pnumber1 = 5;
        int pnumber2 = 5;
        int pstars = 1;
        String pname = "unknown";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the new contact : ");
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

        System.out.print("Enter the Mobile number of new contact : ");
        scanner: while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                pnumber1 = scanner.nextInt();
                if (String.valueOf(pnumber1).length() <= MAX_LENGTH) {
                    break scanner;
                } else {
                    logger.error("ERROR: Input Mobile phone number was too long");
                    System.out.println(
                            "ERROR: Input MOBILE number was too long at" + String.valueOf(pnumber2) + "characters");
                    System.out.print("Please, enter the Mobile phone number again : ");
                }
            } else {
                logger.error("ERROR: Invalid Input. Please, enter only number");
                System.out.print("Please, enter the Mobile phone number again : ");
                scanner.next();
            }
        }
        System.out.print("Enter office number of new contact : ");
        scanner: while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                pnumber2 = scanner.nextInt();
                if (String.valueOf(pnumber2).length() <= MAX_LENGTH) {
                    break scanner;
                } else {
                    logger.error("ERROR: Input office number was too long");
                    System.out.println(
                            "ERROR: Input office number was too long at" + String.valueOf(pnumber2) + "characters");
                    System.out.print("Please, enter the Office phone number again : ");
                }
            } else {
                logger.error("ERROR: Invalid Input. Please, enter only number");
                System.out.print("Please, enter the Office phone number number again : ");
                scanner.next();
            }
        }
        contactsDao phoneInserter = new contactDaoImplemented();
        phoneInserter.insertNewContact(pname.toUpperCase(), pnumber1, pnumber2, pstars);
        System.out.println(
                "\n ==================================== DATA ENTRY IS COMPLETE ======================================\n");
    }
}
