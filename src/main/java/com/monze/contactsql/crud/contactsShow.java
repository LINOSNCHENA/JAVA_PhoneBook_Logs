package com.monze.contactsql.crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class contactsShow {
  static String idAudit, nameAudit, numbAudit;
  static int animals;

  public static void showContact(String url, String user, String passwd) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    final int MAX_LENGTH = 15;
    String pname = "";

    //////////////////////////////////////////////// NAME
      System.out.print("Enter a name of the contact : ");
    scanner: while (scanner.hasNext()) {
      if (scanner.hasNextLine()) {
        pname = scanner.nextLine();
        if (String.valueOf(pname).length() <= MAX_LENGTH) {
          break scanner;
        } else {
          System.out.println("ERROR: Input CONTACT NAME was too long");

          System.out.print("Enter a name of the contact : ");
        }
      } else {
        System.out.println("ERROR: Invalid Input, ONLY ABC Characters");
        System.out.print("Enter a name of the contact : ");
        scanner.next();
      }
    }
    System.out.println(
        "Valid input! You entered: " + pname + ", a number less than or equal to " + MAX_LENGTH + " characters long");

    String queryf2 = "SELECT * FROM tblphone WHERE pname LIKE '%" + pname + "%'";

    try (Connection con = DriverManager.getConnection(url, user, passwd); Statement st = con.createStatement();) {
      ResultSet rs = st.executeQuery(queryf2);

      System.out.println("\n Opened database successfully. \n Loading Data .......\n");

      while (rs.next()) {
        System.out.print(rs.getInt(1));
        System.out.print(" : ");
        System.out.print(rs.getString(2));
        System.out.print(" : ");
        System.out.print(rs.getString(3));
        System.out.print(" : ");
        System.out.println(rs.getString(4));
        idAudit = rs.getString("id");
        nameAudit = rs.getString("pname");
        numbAudit = rs.getString("pnumber1");
      }
      rs.close();
      st.close();

    } catch (SQLException ex) {
      Logger lgr = Logger.getLogger(contactsShow.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
    System.out.println("\n !======================== SHOWING COMPLETE =========================!\n");

  }

  //////////////////////////////////////////////////// SECOND
  public static String getRecordAudit(String url, String user, String passwd) throws SQLException {
    showContact(url, user, passwd);
    System.out.println("TEST PROOF - ID-XX : " + idAudit);
    System.out.println("TEST PROOF - NAME  : " + nameAudit);
    System.out.println("TEST PROOF - NUMBER: " + numbAudit);
    System.out.println("\n !======================== PROOF =========================!\n");
    return numbAudit;
  }
  ///////////////////////////////////////////////////// THIRD
  public static int numberOfAnimals(String url, String user, String passwd) throws SQLException { // CRUD 3
    String query3 = "SELECT COUNT(*) FROM  tblphone";
    try (Connection con = DriverManager.getConnection(url, user, passwd); Statement st1 = con.createStatement();) {
      ResultSet rs = st1.executeQuery(query3);
      if (rs.next()) {
        animals = rs.getInt(1);
        System.out.println("\n !============== FOUND TOTAL OF " + animals + " CONTACTS=======================!\n");
        return rs.getInt(1);
      }
    }
    return 0;
  }
}
