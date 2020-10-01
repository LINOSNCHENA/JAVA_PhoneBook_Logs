package com.apiRepos.servicesApi;

import java.util.Scanner;

import com.apiRepos.contactDaoImplemented;
import com.apiRepos.contactsDao;
import com.apiRepos.logAdminiImplemented;

import org.apache.log4j.Logger;

public class dataSearch {
  final static Logger logger = Logger.getLogger(dataSearch.class);
  final static Logger logSearch = Logger.getLogger(dataSearch.class.getName());

  public void findAllData() {
    contactsDao phoneSearcher = new contactDaoImplemented();
    phoneSearcher.findAllContacts();
    System.out.println(
        "\n ================================== DATA_SEARCH IS COMPLETE =======================================\n");
  }

  public void findOneData() {

    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 20;
    int id = 0;
    System.out.print("Enter the contact identity number to search : ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        id = scanner.nextInt();
        if (String.valueOf(id).length() <= MAX_LENGTH) {
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
    contactsDao phoneSearcher = new contactDaoImplemented();
    phoneSearcher.findContactById(id);
    System.out.println(
        "\n =================================== DATA_ID SEARCH IS COMPLETE ===================================\n");
  }

  public void findNameData() {

    contactsDao phoneSearcher = new contactDaoImplemented();
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
          System.out.print("Enter a name of the contact : ");
        }
      } else {
        logger.error("ERROR: Invalid Input, Only alpabetical characters allowed");
        System.out.print("Enter a name of the contact : ");
        scanner.next();
      }
    }
    try {
      phoneSearcher.findContactByName(nameX);
    } catch (Exception ex) {
      logger.error("Sorry, - something wrong!", ex);
    }
    System.out.println(
        "\n ================================ NAME_DATA SEARCH COMPLETE =======================================\n");
  }

  public void deleteData() {
    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 20;
    int id = 0;
    System.out.print("Enter the contact identity number to delete : ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        id = scanner.nextInt();
        if (String.valueOf(id).length() <= MAX_LENGTH) {
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
    contactsDao phoneSearcher = new contactDaoImplemented();
    phoneSearcher.deleteOneContact(id);
    System.out.println(
        "\n ==================================== DATA_DELETION IS COMPLETE ===================================\n");
  }

  public void deleteAllContacts() {
    contactsDao phoneSearcher = new contactDaoImplemented();
    phoneSearcher.deleteAllContacts();
  }

  public void deleteAllLogs() {
    logAdminiImplemented phoneLogs = new logAdminiImplemented();
    phoneLogs.deleteAllLogs();
    System.out.println(
        "\n ================================ DELETING_ALL_CONTACTS_&_LOGS_COMPLETE ===============================\n");
  }

  public void findLogs() {
    logAdminiImplemented phoneLogs = new logAdminiImplemented();
    phoneLogs.findAllLogs();
    System.out.println(
        "\n ==================================== FIND_LOGS  COMPLETE ============================================\n");

  }

}
