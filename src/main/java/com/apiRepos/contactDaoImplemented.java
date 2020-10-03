package com.apiRepos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dbase.connFactory;
import com.dbase.dbConfig;
import com.model.Contact;

import org.apache.log4j.Logger;

public class contactDaoImplemented implements contactsDao {

    final static Logger logger = Logger.getLogger(contactsDao.class);

    private final Connection connection = connFactory.getConnection(dbConfig.url, dbConfig.user, dbConfig.passwd);
    private static final String INSERT_INTO_CONTACTS = "INSERT INTO CONTACTSLIST (NAME, MOBILE,OFFICE,STARS) VALUES (?,?,?,?)";
    private static final String FIND_CONTACT_BY_ID = "SELECT * FROM CONTACTSLIST WHERE ID=? order by ID";
    private static final String FIND_CONTACT_BY_NAME = "SELECT * FROM CONTACTSLIST WHERE NAME LIKE '%";
    private static final String FIND_ALL_CONTACTS = "SELECT * FROM CONTACTSLIST order by ID";
    private static final String UPDATE_ONE_CONTACT = "UPDATE CONTACTSLIST SET NAME=?, MOBILE=?,OFFICE=?,STARS=? WHERE ID=?";
    private static final String DELETE_ONE_CONTACT = "DELETE FROM CONTACTSLIST WHERE id = ?";
    private static final String COUNT_CONTACTS = "SELECT COUNT(*) FROM CONTACTSLIST";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS CONTACTSLIST";
    private static final String CREATE_TABLE = "CREATE TABLE CONTACTSLIST (ID SERIAL PRIMARY KEY,NAME TEXT NOT NULL, MOBILE BIGINT NOT NULL,OFFICE BIGINT,STARS INT DEFAULT 45,CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
    private static final String INSERT1_TABLE = "INSERT INTO CONTACTSLIST (NAME,MOBILE,OFFICE,STARS) VALUES('POLICE', 9812345,6654321,4),('HOSPDEPT',8912020,9912356,2)";
    private static final String INSERT2_TABLE = "INSERT INTO CONTACTSLIST (NAME,MOBILE,OFFICE,STARS) VALUES('FIRES', 8912345,7754321,4),('AMBULANCE',8820201,8812342,3)";

    @Override
    public void insertNewContact(String name, int mobile, int office, int stars) {
        insertContact(name, mobile, office, stars);
    }

    @Override
    public List<Contact> findAllContacts() {
        return findContacts();
    }

    @Override
    public Contact findContactByName(String name) {
        return findByName(name);
    }

    @Override
    public Contact findContactById(Integer id) {
        return findById(id);
    }
    @Override
    public int countAllContacts() {
        return countContacts();
    }

    @Override
    public void updateOneContact(Integer id, String name, int mobile, int office, int stars) {
        updateContact(id, name, mobile, office, stars);
    }
 

    @Override
    public void deleteOneContact(Integer id) {
        deleteContact(id);
    }

    @Override
    public void deleteAllContacts() {
        deleteContacts();
    }

    public void insertContact(String name, int mobile, int office, int stars) {

        PreparedStatement statementInsert;

        try {
            statementInsert = connection.prepareStatement(INSERT_INTO_CONTACTS);
            statementInsert.setString(1, name);
            statementInsert.setInt(2, mobile);
            statementInsert.setInt(3, office);
            statementInsert.setInt(4, stars);

            statementInsert.executeUpdate();
            statementInsert.close();
            logger.info("Contact was inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
    }

    private List<Contact> printSearchResult(ResultSet resultSet) {
        List<Contact> contactsFound = new ArrayList<>();
        try {
            java.sql.ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columsIdentified = resultSetMetaData.getColumnCount();
            System.out.println(
                    "\n =====================================================================================================");
            for (int i = 1; i <= columsIdentified; i++) {
                System.out.printf("%-8s\t", resultSetMetaData.getColumnName(i));
            }
            System.out.println(
                    "\n =====================================================================================================\n");
            if (resultSet.next()) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    for (int i = 1; i <= columsIdentified; i++) {
                        System.out.printf("%-8s\t", resultSet.getObject(i));
                    }
                    System.out.println();
                }
            } else {
                System.out.println("Ooops! No such records found in the database");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        return contactsFound;
    }

    private Contact findById(Integer id) {
        PreparedStatement statementId;
        Contact searchedId = null;

        try {
            statementId = connection.prepareStatement(FIND_CONTACT_BY_ID, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statementId.setInt(1, id);
            List<Contact> items = printSearchResult(statementId.executeQuery());
            if (items.size() > 0) {
                searchedId = items.get(0);
            } else {
                logger.warn("No such contact in the database");
            }
            statementId.close();
            logger.info("One contact was found successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        return searchedId;
    }

    private Contact findByName(String name) {
        PreparedStatement statementName;
        Contact searchedName = null;

        try {

            statementName = connection.prepareStatement(FIND_CONTACT_BY_NAME + name + "%'",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            List<Contact> items = printSearchResult(statementName.executeQuery());

            if (items.size() > 0) {
                searchedName = items.get(0);
            } else {
                logger.warn("No such contact in the database");
            }
            statementName.close();
            logger.info("Contact name was located successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        return searchedName;
    }

    private List<Contact> findContacts() {
        List<Contact> contactsloaded = new ArrayList<>();
        Statement statementContacts;

        try {
            statementContacts = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            contactsloaded = printSearchResult(statementContacts.executeQuery(FIND_ALL_CONTACTS));
            if (contactsloaded.size() == 0) {

                logger.warn("No contacts exist currently");
            }
            statementContacts.close();
            logger.info("Contacts located successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        return contactsloaded;
    }

    private int countContacts() {
        PreparedStatement statementCounter;
        int contactCounter = 0;
        try {
            statementCounter = connection.prepareStatement(COUNT_CONTACTS);
            ResultSet counterResults = statementCounter.executeQuery();
            counterResults.next();
            contactCounter = counterResults.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("         Total Number of records is :  " + contactCounter);
        return contactCounter;
    }

    private void updateContact(Integer id, String name, int mobile, int office, int stars) {
        PreparedStatement statementUpdate;
        try {
            statementUpdate = connection.prepareStatement(UPDATE_ONE_CONTACT);
            statementUpdate.setString(1, name);
            statementUpdate.setInt(2, mobile);
            statementUpdate.setInt(3, office);
            statementUpdate.setInt(4, stars);
            statementUpdate.setInt(5, id);
            statementUpdate.executeUpdate();
            statementUpdate.close();
            logger.info("Contact was updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
    }

    private void deleteContact(Integer id) {
        PreparedStatement statementDelete;
        try {
            statementDelete = connection.prepareStatement(DELETE_ONE_CONTACT);
            statementDelete.setInt(1, id);
            statementDelete.executeUpdate();
            statementDelete.close();
            logger.info("Contact was deleted succesfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
    }

    private void deleteContacts() {
        try {
            Statement stmt1 = connection.createStatement();
            Statement stmt2 = connection.createStatement();
            Statement stmt3 = connection.createStatement();
            Statement stmt4 = connection.createStatement();
            stmt1.executeUpdate(DROP_TABLE);
            stmt2.executeUpdate(CREATE_TABLE);
            stmt3.executeUpdate(INSERT1_TABLE);
            stmt4.executeUpdate(INSERT2_TABLE);
            stmt1.close();
            stmt2.close();
            stmt3.close();
            stmt4.close();
            logger.info("All contacts deleted successfuly");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            logger.error("Sorry, - something went wrong!", e);
        }
    }
}
