package com.monze.contactsql.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class contactsDelete {

    public static void deleteContact(String url, String user, String passwd) {

        /////////////////////////////////////////// ENTER CONTACT ID# TO DELETE
        Scanner scanner = new Scanner(System.in);
        final int MAX_LENGTH = 15;
        int id = 0;
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
        ///////////////////////////////////////////////////////////////////////////////////////
        String query1 = "DELETE FROM tblphone WHERE id =?";
        try (Connection con = DriverManager.getConnection(url, user, passwd);
                PreparedStatement st = con.prepareStatement(query1)) {
            st.setInt(1, id);
            int rowsDeleted = st.executeUpdate();
            System.out.println(rowsDeleted + " Record-rows deleted");
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger lgr = Logger.getLogger(contactsDelete.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        System.out.println("\n !======================== DELETION COMPLETE ========================!\n");

    }
}
