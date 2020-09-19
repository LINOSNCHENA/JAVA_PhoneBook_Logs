package com.apiRepos.servicesApi;

import java.util.Scanner;
import com.apiRepos.contactDaoImplemented;
import com.apiRepos.contactsDao;


public class dataEdit {
  public void updateData() {
    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 20;
    int id = 0;
    int pnumber1 = 0;
    int pnumber2 = 0;
    String pname = "UNKNOWN";

    //////////////////////////////////////////////////////////////////////////// NAME

    System.out.print("Enter an EDIT name of the contact : ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextLine()) {
        pname = scanner.nextLine();
        if (String.valueOf(pname).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          System.out.println("ERROR: Input contact name was too long");
          System.out.print("Enter a name of the contact : ");
        }
      } else {
        System.out.println("ERROR: Invalid Input, Only alpabetical characters allowed");
        System.out.print("Enter a name of the contact : ");
        scanner.next();
      }
    }

    /////////////////////////////////////////////////////////////////////////// IDENTIFICATION

    System.out.print("Enter the EDIT contact identity number to edit: ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        id = scanner.nextInt();
        if (String.valueOf(id).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          System.out.println("ERROR: Input identity does not exist");
          System.out.print("Enter the correct identity number: ");
        }
      } else {
        System.out.println("ERROR: Invalid Input. Please, enter only number");
        System.out.print("Enter the correct identity number: ");
        scanner.next();
      }
    }

    ////////////////////////////////////////////////////////////////////////// NUMBERS

    System.out.print("Enter the EDIT MOBILE PHONE number: ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        pnumber1 = scanner.nextInt();
        if (String.valueOf(pnumber1).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          System.out.println("ERROR: Input MOBILE number was too long");
          System.out.println("ERROR: Input MOBILE number was too long at" + String.valueOf(pnumber2) + "characters");
          System.out.print("Please, enter the MOBILE PHONE number again : ");
        }
      } else {
        System.out.println("ERROR: Invalid Input. Please, enter only number");
        System.out.print("Please, enter the MOBILE PHONE number again : ");
        scanner.next();
      }
    }
    System.out.print("Enter the EDIT OFFICE PHONE number: ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        pnumber2 = scanner.nextInt();
        if (String.valueOf(pnumber2).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          System.out.println("ERROR: Input OFFICE number was too long");
          System.out.println("ERROR: Input OFFICE number was too long at" + String.valueOf(pnumber2) + "characters");
          System.out.print("Please, enter the OFFICE PHONE number again : ");
        }
      } else {
        System.out.println("ERROR: Invalid Input. Please, enter only number");
        System.out.print("Please, enter the OFFICE PHONE number again : ");
        scanner.next();
      }
    }
    contactsDao phoneDefaults = new contactDaoImplemented();
    phoneDefaults.updateOneContact(id, pname.toUpperCase(), pnumber1, pnumber2);

    System.out.println("\n !======================= DATA_EDIT #2 COMPLETE ===========================!\n");
    //////////////////////////////////////////////////////////////////////////////////// END
  }
}
