package com.monze.contactsql.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class contactsListing {

    public static void readAllContacts(String url, String user, String passwd) {
   // List all the contacts
        try (Connection con = DriverManager.getConnection(url, user, passwd);
                PreparedStatement pst = con.prepareStatement("SELECT * FROM tblphone ORDER BY id");
                ResultSet rs = pst.executeQuery()) {
            System.out.println("\n Opened database successfully.\n Loading Data .......\n");
            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(" : ");
                System.out.print(rs.getString(2));
                System.out.print(" : ");
                System.out.print(rs.getString(3));
                System.out.print(" : ");
                System.out.println(rs.getString(4));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(contactsListing.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        System.out.println("\n !======================== LISTING COMPLETE =========================!\n");
   
    }

}
