package com.apiRepos.servicesApi;

import java.util.Scanner;

import com.apiRepos.contactDaoImplemented;
import com.apiRepos.contactsDao;
import com.apiRepos.logBookImplemented;

import org.apache.log4j.Logger;

public class dataSearch {
  final static Logger logger = Logger.getLogger(dataSearch.class);
  final static Logger log = Logger.getLogger(dataSearch.class.getName());

  public void findAllData() {
    ////////////////////////////////////////////////////////////////////////////////// FIND
    ////////////////////////////////////////////////////////////////////////////////// ALL

    contactsDao phoneDefaults = new contactDaoImplemented();
    phoneDefaults.findAllContacts();
    System.out.println("\n !======================= ALL DATA_FOUND COMPLETE =======================!\n");
  }

  public void findOneData() {
    /////////////////////////////////////////////////////////////////////////// FIND
    /////////////////////////////////////////////////////////////////////////// ONE
    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 20;
    int id = 0;
    System.out.print("Enter the FIND contact identity number to edit: ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        id = scanner.nextInt();
        if (String.valueOf(id).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          /// lgr.log(Level.SEVERE, null, id);
          logger.error("ERROR: Input identity does not exist");
          System.out.print("Enter the correct identity number: ");
        }
      } else {
        logger.error("ERROR: Invalid Input. Please, enter only number");
        System.out.print("Enter the correct identity number: ");
        scanner.next();
      }
    }
    contactsDao phoneDefaults = new contactDaoImplemented();
    phoneDefaults.findContactById(id);
    System.out.println("\n !======================= DATA_FOUND ID COMPLETE ========================!\n");
  }

  public void findNameData() {

    /////////////////////////////////////////////////////////////////////////////// FIND
    /////////////////////////////////////////////////////////////////////////////// NAME
    contactsDao phoneDefaults = new contactDaoImplemented();
    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 20;
    String pname = "";
    System.out.print("Enter an EDIT name of the contact : ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextLine()) {
        pname = scanner.nextLine();
        if (String.valueOf(pname).length() <= MAX_LENGTH) {
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
      phoneDefaults.findContactByName(pname);
    } catch (Exception ex) {
       logger.error("Sorry, - something wrong!", ex);
    }
    System.out.println("\n !======================= NAME_DATA  COMPLETE ============================!\n");
  }

  public void deleteData() {
    /////////////////////////////////////////////////////////////////////////// DELETE
    /////////////////////////////////////////////////////////////////////////// ONE
    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 20;
    int id = 0;
    System.out.print("Enter the DELETE contact identity number to edit: ");
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
    contactsDao phoneDefaults = new contactDaoImplemented();
    phoneDefaults.deleteOneContact(id);
    System.out.println("\n !==================== DELETED_ONE_DATA #2 COMPLETE =======================!\n");
  }

  public void deleteAllData() {///////////////////////////////////////////////////////////////// DELETE ALL
    contactsDao phoneDefaults = new contactDaoImplemented();
    phoneDefaults.deleteAllContacts();
    System.out.println("\n !======================= DELETED_ALL_DATA COMPLETE =======================!\n");
  }

  public void formatData() {///////////////////////////////////////////////////////////////////// FORMAT
    contactsDao phoneDefaults = new contactDaoImplemented();
    phoneDefaults.formatContactsTable();
    System.out.println("\n !======================= FORMAT_ALL_DATA COMPLETE ========================!\n");
  
  }


 ////////////////////////////////////////////////////////////////////////////////////////////////// FORMAT LOGS X2

  public void formatLogs() {
    logBookImplemented phoneLogs = new logBookImplemented();
    phoneLogs.formatLogsTable();
  //  log.info("Formating Task computation complete >>> Test1");  
    System.out.println("\n !======================= FORMAT_LOGS  COMPLETE =======================!\n");
  }

  public void findLogs() {
  logBookImplemented phoneLogs = new logBookImplemented();
  phoneLogs.findAllLogs();
  System.out.println("\n !======================= FIND_LOGS  COMPLETE ========================!\n");
 
}


}
