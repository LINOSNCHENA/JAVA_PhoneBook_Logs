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
    private static final String INSERT_INTO_CONTACTS = "INSERT INTO CONTACTSLIST (nameX, mobileX,officeX,starX) VALUES (?,?,?,?)";
    private static final String FIND_CONTACT_BY_ID = "SELECT * FROM CONTACTSLIST WHERE id=? order by id";
    private static final String FIND_CONTACT_BY_NAME = "SELECT * FROM CONTACTSLIST WHERE nameX LIKE '%";
    private static final String FIND_ALL_CONTACTS = "SELECT * FROM CONTACTSLIST order by id";
    private static final String UPDATE_ONE_CONTACT = "UPDATE CONTACTSLIST SET nameX=?, mobileX=?,officeX=?,starX=? WHERE id=?";
    private static final String DELETE_ONE_CONTACT = "DELETE FROM CONTACTSLIST WHERE id = ?";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS CONTACTSLIST";
    private static final String CREATE_TABLE = "CREATE TABLE CONTACTSLIST (id SERIAL PRIMARY KEY,nameX TEXT NOT NULL, mobileX BIGINT NOT NULL,officeX BIGINT,starX int DEFAULT 45,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
    private static final String INSERT1_TABLE = "INSERT INTO CONTACTSLIST (nameX,mobileX,officeX,starX) VALUES('POLICE-E', 1992020,2234678,4),('HOSPITAL-E',2882020,12356,2)";
    private static final String INSERT2_TABLE = "INSERT INTO CONTACTSLIST (nameX,mobileX,officeX,starX) VALUES('FIRE-E', 37720280,123489,4),('AMBULANCE-P',46662020,112342,3)";

    @Override
    public void insertNewContact(String nameX, int mobileX, int officeX, int starX) {
        insertContact(nameX, mobileX, officeX, starX);
    }

    @Override
    public List<Contact> findAllContacts() {
        return findAll();
    }

    @Override
    public Contact findContactByName(String nameX) {
        return findByName(nameX);
    }

    @Override
    public Contact findContactById(Integer id) {
        return findById(id);
    }

    @Override
    public void updateOneContact(Integer id, String nameX, int mobileX, int officeX, int starX) {
        updateContact(id, nameX, mobileX, officeX, starX);
    }

    @Override
    public void deleteOneContact(Integer id) {
        deleteContact(id);
    }

    @Override
    public void deleteAllContacts() {
        resetContacts();
    }

    public void insertContact(String nameX, int mobileX, int officeX, int starX) {

        PreparedStatement deleteContactX;

        try {
            deleteContactX = connection.prepareStatement(INSERT_INTO_CONTACTS);
            deleteContactX.setString(1, nameX);
            deleteContactX.setInt(2, mobileX);
            deleteContactX.setInt(3, officeX);
            deleteContactX.setInt(4, starX);

            deleteContactX.executeUpdate();
            deleteContactX.close();
            logger.info("One contact was inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
    }

    private Contact findById(Integer id) {
        PreparedStatement deleteContactX;
        Contact searchedItem = null;

        try {
            deleteContactX = connection.prepareStatement(FIND_CONTACT_BY_ID, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            deleteContactX.setInt(1, id);
            List<Contact> items = getAllContactsSought(deleteContactX.executeQuery());
            if (items.size() > 0) {
                searchedItem = items.get(0);
            } else {
                logger.warn("No such contact exist in the database");
            }
            deleteContactX.close();
            logger.info("One contact id was found successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);

        }
        return searchedItem;
    }

    private Contact findByName(String nameX) {
        PreparedStatement deleteContactX;
        Contact searchedItem = null;

        try {

            deleteContactX = connection.prepareStatement(FIND_CONTACT_BY_NAME + nameX + "%'",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            List<Contact> items = getAllContactsSought(deleteContactX.executeQuery());
            if (items.size() > 0) {
                searchedItem = items.get(0);
            } else {
                logger.warn("No such contact exist in the database");
            }
            deleteContactX.close();
            logger.info("Some contact name was located successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        return searchedItem;
    }

    private List<Contact> findAll() {
        List<Contact> contactsFound = new ArrayList<>();
        Statement deleteContactX;

        try {
            deleteContactX = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            contactsFound = getAllContactsSought(deleteContactX.executeQuery(FIND_ALL_CONTACTS));
            if (contactsFound.size() == 0) {

                logger.warn("No contacts exist currently");
            }
            deleteContactX.close();
            logger.info("A list of contacts located successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        List<Contact> result1 = null;
        return result1;
    }

    private List<Contact> getAllContactsSought(ResultSet resultSet) {

        List<Contact> contactsFound = new ArrayList<>();
        try {
            java.sql.ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columsIdentified = resultSetMetaData.getColumnCount();
            System.out.println(
                    "\n =====================================================================================================\n");
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

    private void updateContact(Integer id, String nameX, int mobileX, int officeX, int starX) {
        PreparedStatement updateContactX;
        Contact contactToUpdate = findContactById(id);
        if (contactToUpdate == null) {
            logger.warn("The contact Id number " + contactToUpdate + " is non-existant");
        }
        try {
            updateContactX = connection.prepareStatement(UPDATE_ONE_CONTACT);
            updateContactX.setString(1, nameX);
            updateContactX.setInt(2, mobileX);
            updateContactX.setInt(3, officeX);
            updateContactX.setInt(4, starX);
            updateContactX.setInt(5, id);
            updateContactX.executeUpdate();
            logger.info("One contact was updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
    }

    private void deleteContact(Integer id) {
        PreparedStatement deleteContactX;
        Contact contactToDelete = findContactById(id);

        if (contactToDelete == null) {
            logger.warn("The contact Id number " + contactToDelete + " is non-existant");
        } else {
            try {
                deleteContactX = connection.prepareStatement(DELETE_ONE_CONTACT);
                deleteContactX.setInt(1, id);
                deleteContactX.executeUpdate();
                deleteContactX.close();
                logger.info(" One contact was deleted succesfully");
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Sorry, - something went wrong!", e);
            }
        }
    }

    private void resetContacts() {
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
            logger.info("All contacts records have been formated successfuly");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            logger.error("Sorry, - something went wrong!", e);
        }
    }
}
