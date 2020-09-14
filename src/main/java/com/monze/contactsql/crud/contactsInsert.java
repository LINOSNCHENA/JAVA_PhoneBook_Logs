package com.monze.contactsql.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class contactsInsert {

  public static void insertContact(String url, String user, String passwd) {
    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 20;
    int pnumber1 = 0;
    int pnumber2 = 0;
    String pname = "";

    //////////////////////////////////////////////// = = NAME
    System.out.print("Enter a name of the contact : ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextLine()) {
        pname = scanner.nextLine().trim();
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

    /////////////////////////////////// //////////////////////////// -- TWO NUMBERS

    System.out.print("Enter the MOBILE PHONE number: ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        pnumber1 = scanner.nextInt();
        if (String.valueOf(pnumber1).length() <= MAX_LENGTH) {
          //if ((pnumber1).size() <= MAX_LENGTH) {
          break scanner;
        } else {
          System.out.println("ERROR: Input MOBILE number was too long at"+String.valueOf(pnumber1)+"characters");
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

    ////////////////////////////////////////////////////////////////////////////////////// END

    String query = "INSERT INTO tblphone (pname, pnumber1, pnumber2 ) VALUES ( ?,?, ?)";

    try (Connection con = DriverManager.getConnection(url, user, passwd);
        PreparedStatement pst = con.prepareStatement(query)) {
      System.out.println("\n Opened database successfully.\n Writing contact data .......\n");
      pst.setString(1, pname);
      pst.setInt(2, pnumber1);
      pst.setInt(3, pnumber2);
      pst.executeUpdate();
      pst.close();

    } catch (SQLException ex) {
      ex.printStackTrace();
      Logger lgr = Logger.getLogger(contactsInsert.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
    System.out.println("\n !======================= INSERTION COMPLETE ========================!\n");

  }

}
