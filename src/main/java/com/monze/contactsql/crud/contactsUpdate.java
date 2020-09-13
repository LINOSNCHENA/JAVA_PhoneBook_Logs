package com.monze.contactsql.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class contactsUpdate {

  public static void updateContact(String url, String user, String passwd) {
    // ENTER the ID of CONTACT to be EDITED
    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 15;
    int id = 0;
    String pname = "";
    int pnumber1 = 0;
    int pnumber2 = 0;

    ////////////////////////////////////////////////  == NAME
    System.out.print("Enter a name of the contact : ");
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

    ///////////////////////////////////////////////////// IDIDIDIDID - IDENTIFICATION
    System.out.print("Enter the contact identity number: ");
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
    /////////////////////////////////// ///////////////////////////////////////////////////// = TWO NUMBERS

    System.out.print("Enter the MOBILE PHONE number: ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        pnumber1 = scanner.nextInt();
        if (String.valueOf(pnumber1).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          System.out.println("ERROR: Input MOBILE number was too long");
          System.out.println("ERROR: Input MOBILE number was too long at"+String.valueOf(pnumber2)+"characters");
          System.out.print("Please, enter the MOBILE PHONE number again : ");
        }
      } else {
        System.out.println("ERROR: Invalid Input. Please, enter only number");
        System.out.print("Please, enter the MOBILE PHONE number again : ");
        scanner.next();
      }
    }
    System.out.print("Enter the OFFICE PHONE number: ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        pnumber2 = scanner.nextInt();
        if (String.valueOf(pnumber2).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          System.out.println("ERROR: Input OFFICE number was too long");
          System.out.println("ERROR: Input OFFICE number was too long at"+String.valueOf(pnumber2)+"characters");
          System.out.print("Please, enter the OFFICE PHONE number again : ");
        }
      } else {
        System.out.println("ERROR: Invalid Input. Please, enter only number");
        System.out.print("Please, enter the OFFICE PHONE number again : ");
        scanner.next();
      }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////// = end 
    String query = "UPDATE tblphone " + "SET pname =?, pnumber1 = ?,pnumber2 = ? " + "WHERE id = ?";
    try (Connection con = DriverManager.getConnection(url, user, passwd);
        PreparedStatement pst = con.prepareStatement(query)) {
      pst.setString(1, pname);
      pst.setInt(2, pnumber1);
      pst.setInt(3, pnumber2);
      pst.setInt(4, id);
      pst.executeUpdate();

    } catch (SQLException ex) {
      ex.printStackTrace();
      Logger lgr = Logger.getLogger(contactsUpdate.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
    System.out.println("\n !======================== EDITING COMPLETE =========================!\n");
  }

}
