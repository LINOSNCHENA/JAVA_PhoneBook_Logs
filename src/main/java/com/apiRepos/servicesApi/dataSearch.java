package com.apiRepos.servicesApi;

import java.util.Scanner;
import com.apiRepos.contactDaoImplemented;
import com.apiRepos.contactsDao;
import com.apiRepos.logAdminiImplemented;
import org.apache.log4j.Logger;

public class dataSearch {

  contactsDao phoneSearcher = new contactDaoImplemented();
  logAdminiImplemented phoneLogs = new logAdminiImplemented();

  final static Logger logger = Logger.getLogger(dataEntry.class);

  public void findIdRecord() {
    Scanner scanner = new Scanner(System.in);
    int Id = 0;
    final int MAX_COUNT = phoneSearcher.countAllContacts();
    System.out.print("Enter the contact identity number to search : ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        Id = scanner.nextInt();
        if (String.valueOf(Id).length() <= MAX_COUNT) {
          break scanner;
        } else {
          logger.error("ERROR: Such identity number does not exist");
          System.out.println("ERROR: Such identity number does not exist");
          System.out.print("Enter the correct identity number: ");
        }
      } else {
        logger.error("ERROR: Invalid Input. Please, only numbers allowed");
        System.out.println("ERROR: Invalid Input, Only Numerical characters allowed");
        System.out.print("Enter the correct identity number: ");
        scanner.next();
      }
    }

    phoneSearcher.findContactById(Id);
    System.out.println(
        "\n =================================== DATA_ID SEARCH IS COMPLETE ===================================\n");
  }

  public void findNameData() {

    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 20;
    String pname = "";
    String nameX = "";
    System.out.print("Enter a name of the contact to search : ");

    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextLine()) {
        pname = scanner.nextLine();
        nameX = pname.toUpperCase();
        if (String.valueOf(nameX).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          logger.error("ERROR: Input contact name was too long");
          System.out.print("ERROR: Input contact name was too long");
          System.out.print("Enter a name of the contact : ");
        }
      } else {
        logger.error("ERROR: Invalid Input, Only alpabetical characters allowed");
        System.out.print("ERROR: Invalid Input, Only alpabetical characters allowed");
        System.out.print("Enter a name of the contact : ");
        scanner.next();
      }
    }
    phoneSearcher.findContactByName(nameX);
    System.out.println(
        "\n ================================ NAME_DATA SEARCH COMPLETE =======================================\n");
  }

  public void deleteData() {
    Scanner scanner = new Scanner(System.in);
    int Id = 0;
     final int MAX_COUNT = phoneSearcher.countAllContacts();
    System.out.print("Enter the contact identity number to delete : ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        Id = scanner.nextInt();
        if (String.valueOf(Id).length() <= MAX_COUNT) {
          break scanner;
        } else {
          logger.error("ERROR: Input identity does not exist");
          System.out.print("Enter the correct identity number: ");
        }
      } else {
        logger.error("ERROR: Invalid Input. Please, enter only number");
        System.out.print("Enter the correct identity number: ");
        scanner.next();
      }
    }
    phoneSearcher.deleteOneContact(Id);
    System.out.println(
        "\n ==================================== DATA_DELETION IS COMPLETE ===================================\n");
  }

  public void findAllData() {
    phoneSearcher.findAllContacts();
    System.out.println(
        "\n ================================== DATA_SEARCH IS COMPLETE =======================================\n");
  }

  public int countAllContacts() {
    return phoneSearcher.countAllContacts();
  }
  public void countAllClosing() {
    System.out.println(
        "\n ================================== COUNTING_IS COMPLETE =======================================\n");

  }

  public void findLogs() {
    phoneLogs.findAllLogs();
    System.out.println(
        "\n ==================================== FIND_LOGS  COMPLETE ============================================\n");

  }

  public void deleteAllContactsAndLogs() {
    phoneSearcher.deleteAllContacts();
    phoneLogs.deleteAllLogs();
    System.out.println(
        "\n ================================ DELETING_ALL_CONTACTS_&_LOGS_COMPLETE ===============================\n");
  }

}
