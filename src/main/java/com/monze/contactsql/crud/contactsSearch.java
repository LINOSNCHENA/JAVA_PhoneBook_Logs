package com.monze.contactsql.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class contactsSearch {
  public static void findContact(String url, String user, String passwd) {
    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 20;
    int id = 0;
    //////////////////////// IDIDIDIDIDID
    System.out.print("Enter the CONTACT ID-NUMBER number: ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        id = scanner.nextInt();
        if (String.valueOf(id).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          System.out.println("ERROR: Input MOBILE number was too long");
          System.out.print("Enter the MOBILE PHONE number: ");
        }
      } else {
        System.out.println("ERROR: Invalid Input. Please, enter only number");
        System.out.print("Enter the MOBILE PHONE number: ");
        scanner.next();
      }
    }
    String queryf2 = "SELECT * FROM tblphone WHERE id = ?";
    try (Connection con = DriverManager.getConnection(url, user, passwd);
        PreparedStatement st = con.prepareStatement(queryf2)) {
      st.setInt(1, id);
      ResultSet rs = st.executeQuery();
      System.out.println("\n Opened database successfully.\n Loading Data .......\n");
      while (rs.next()) {
        System.out.println(" ");
        System.out.print(rs.getInt(1));
        System.out.print(" : ");
        System.out.print(rs.getString(2));
        System.out.print(" : ");
        System.out.print(rs.getString(3));
        System.out.print(" : ");
        System.out.println(rs.getString(4));
      }
      rs.close();
      st.close();
    } catch (SQLException ex) {
      Logger lgr = Logger.getLogger(contactsSearch.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
    System.out.println("\n !======================= SEARCHING COMPLETE ========================!\n");

  }
}
