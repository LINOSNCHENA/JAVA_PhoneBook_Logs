package com.monze.contactsql;

import com.monze.contactsql.crud.contactsDelete;
import com.monze.contactsql.crud.contactsInsert;
import com.monze.contactsql.crud.contactsListing;
import com.monze.contactsql.crud.contactsSearch;
import com.monze.contactsql.crud.contactsShow;
import com.monze.contactsql.crud.contactsUpdate;
import com.monze.contactsql.mgmt.contactsCreateTable;
import com.monze.contactsql.mgmt.contactsManual;
import com.monze.contactsql.startdata.bookDefaultData;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class appRUN {

    private static Properties readProperties() {
        Properties databaseDoors = new Properties();
        String configfile = "src/main/resources/database.properties";
        Path myPath = Paths.get(configfile);

        try {
            BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
            databaseDoors.load(bf);
        } catch (IOException ex) {
            Logger.getLogger(appRUN.class.getName()).log(Level.SEVERE, null, ex);
            log.info("\n Failed to open database! \n");
        }
        return databaseDoors;
    }

    static Logger log = Logger.getLogger(appRUN.class.getName());

    public static void main(String[] args) throws SQLException {

        Properties databaseKeys = readProperties();
        String url = databaseKeys.getProperty("db.url");
        String user = databaseKeys.getProperty("db.user");
        String passwd = databaseKeys.getProperty("db.passwd");

       
        bookDefaultData.bookstarter();
        contactsManual.helpOrMenu(); // Introdoctory Default

        Scanner input = new Scanner(System.in);
        String line = input.nextLine().trim();

        while (!line.equals("exit")) {

            switch (line) {

                case "menu":
                    contactsManual.helpOrMenu();
                    break;
                case "audit":
                    contactsShow.getRecordAudit(url, user, passwd);
                    break;
                case "count":
                    contactsShow.numberOfAnimals(url, user, passwd);
                    break;

                case "help":
                    contactsManual.helpOrMenu();
                    break;
                case "format":
                    contactsCreateTable.createTable(url, user, passwd);
                    break;

                case "add":
                    contactsInsert.insertContact(url, user, passwd);
                    break; // CRUD #1 Create
                case "list":
                    contactsListing.readAllContacts(url, user, passwd);
                    break; // CRUD #2 Read

                case "update":
                    contactsUpdate.updateContact(url, user, passwd);
                    break; // CRUD #3 Update

                case "delete":
                    contactsDelete.deleteContact(url, user, passwd);
                    break; // CRUD #4 Delete
                case "find":
                    contactsSearch.findContact(url, user, passwd);
                    break; // CRUD #4 Delete
                case "show":
                    contactsShow.showContact(url, user, passwd);
                    break; // CRUD #4 Delete

                default: // else wrong id
                    log.info(":   Please select an available command word >>> ");
                    break;
            }
            line = input.nextLine().trim();
            log.info("Task Complete >>> ");

        }
        input.close();

        System.out.println("\n !================= PHONE BOOK EXITED AND CLOSED ====================!\n");
    }
}