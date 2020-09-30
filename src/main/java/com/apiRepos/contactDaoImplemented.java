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

    private final Connection connection = connFactory.getConnection(dbConfig.url, dbConfig.user1, dbConfig.passwd1);
    private static final String INSERT_INTO_CONTACTS = "INSERT INTO tblphone (pname, pnumber1,pnumber2,pstar) VALUES (?,?,?,?)";
    private static final String FIND_CONTACT_BY_ID = "SELECT * FROM tblphone WHERE id=? order by id";
    private static final String FIND_CONTACT_BY_NAME = "SELECT * FROM tblphone WHERE pname LIKE '%";
    private static final String FIND_ALL_CONTACTS = "SELECT * FROM tblphone order by id;";
    private static final String UPDATE_ONE_CONTACT = "UPDATE tblphone SET pname=?, pnumber1=?,pnumber2=? WHERE id=?";

    private static final String DELETE_ONE_CONTACT = "DELETE FROM tblphone WHERE id = ?";
    private static final String DELETE_ALL_CONTACTS = "DELETE FROM tblphone WHERE id > 6;";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS tblphone;";
    private static final String CREATE_TABLE = "CREATE TABLE tblphone (id SERIAL PRIMARY KEY,pname TEXT NOT NULL, pnumber1 BIGINT NOT NULL,pnumber2 BIGINT,pstars INT,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
    private static final String INSERT1_TABLE = "INSERT INTO tblphone(pname,pnumber1,pnumber2,pstars) VALUES('POLICE-E', 1992020,2234678,90),('HOSPITAL-E',2882020,12356,789);";
    private static final String INSERT2_TABLE = "INSERT INTO tblphone(pname,pnumber1,pnumber2,pstars) VALUES('FIRE-E', 37720280,123489,10),('AMBULANCE-P',46662020,112342,020);";

    @Override
    public void insertNewContact(String pname, int pnumber1, int pnumber2) {
        insertContact(pname, pnumber1, pnumber2);
    }

    @Override
    public void updateOneContact(Integer id, String pname, int pnumber1, int pnumber2) {
        updateContact(id, pname, pnumber1, pnumber2);
    }

    @Override
    public List<Contact> findAllContacts() {
        return findAll();
    }

     @Override
    public Contact findContactByName(String pname) {
        return findByName(pname);
    }

    @Override
    public Contact findContactById(Integer id) {
        return findById(id);
    }

    @Override
    public void deleteOneContact(Integer id) {
        deleteContact(id);
    }

    @Override
    public void deleteAllContacts() {
        deleteAll();
    }

    @Override
    public void formatContactsTable() {
        resetContacts();
    }

    public void insertContact(String pname, int pnumber1, int pnumber2) {

        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(INSERT_INTO_CONTACTS);
            statement.setString(1, pname);
            statement.setInt(2, pnumber1);
            statement.setInt(3, pnumber2);

            statement.executeUpdate();
            statement.close();
            logger.info("One contact was inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
    }

    private Contact findById(Integer id) {
        PreparedStatement statement;
        Contact searchedItem = null;

        try {
            statement = connection.prepareStatement(FIND_CONTACT_BY_ID, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, id);
            List<Contact> items = getAllContactsFromResultContacts(statement.executeQuery());
            if (items.size() > 0) {
                searchedItem = items.get(0);
            } else {
                logger.warn("No such contact exist in the database");
            }
            statement.close();
            logger.info("One contact id was found successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);

        }
        return searchedItem;
    }

    private Contact findByName(String pname) {
        PreparedStatement statement;
        Contact searchedItem = null;

        try {

            statement = connection.prepareStatement(FIND_CONTACT_BY_NAME + pname + "%'",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            List<Contact> items = getAllContactsFromResultContacts(statement.executeQuery());
            if (items.size() > 0) {
                searchedItem = items.get(0);
            } else {
                logger.warn("No such contact exist in the database");
            }
            statement.close();
            logger.info("Some contact name was located successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        return searchedItem;
    }

    private List<Contact> findAll() {
        List<Contact> result = new ArrayList<>();
        Statement statement;

        try {
            // statement = connection.createStatement();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = getAllContactsFromResultContacts(statement.executeQuery(FIND_ALL_CONTACTS));
            if (result.size() == 0) {

                logger.warn("No contacts exist currently");
            }
            statement.close();
            logger.info("A list of contacts located successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        List<Contact> result1 = null;
        return result1;
    }

     ////////////////////////////////////////////////////

    private List<Contact> getAllContactsFromResultContacts(ResultSet resultSet) {

        List<Contact> result = new ArrayList<>();
        try {
            java.sql.ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-8s\t", resultSetMetaData.getColumnName(i));
            }
            System.out.println();
            if (resultSet.next()) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.printf("%-8s\t", resultSet.getObject(i));
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No records at the DB. Empty DB !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        return result;
    }

    private void updateContact(Integer id, String pname, int pnumber1, int pnumber2) {

        PreparedStatement pst;

        Contact contactToUpdate = findContactById(id);
        if (contactToUpdate == null) {
            logger.warn("The contact Id number " + contactToUpdate + " is non-existant");
        }

        try {
            pst = connection.prepareStatement(UPDATE_ONE_CONTACT);
            pst.setString(1, pname);
            pst.setInt(2, pnumber1);
            pst.setInt(3, pnumber2);
            pst.setInt(4, id);
            pst.executeUpdate();
            logger.info("One contact was updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
    }

    private void deleteContact(Integer id) {
        PreparedStatement statement;
        Contact contactToDelete = findContactById(id);

        if (contactToDelete == null) {
            logger.warn("The contact Id number " + contactToDelete + " is non-existant");
        } else {
            try {
                statement = connection.prepareStatement(DELETE_ONE_CONTACT);
                statement.setInt(1, id);
                statement.executeUpdate();
                statement.close();
                logger.info(" One contact was deleted succesfully");
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Sorry, - something went wrong!", e);
            }
        }
    }

    private void deleteAll() {
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(DELETE_ALL_CONTACTS);
            statement.executeUpdate();
            logger.info("All contacts deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
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
