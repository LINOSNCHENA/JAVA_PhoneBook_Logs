package com.apiRepos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbase.connFactory;
import com.dbase.dbConfig;
import com.model.Log;

import org.apache.log4j.Logger;

public class logAdminiImplemented implements logBook {

    final static Logger logger = Logger.getLogger(logBook.class);

    private final Connection connection = connFactory.getConnection(dbConfig.url, dbConfig.user, dbConfig.passwd);

    private static final String DROP_LOGS_TABLE = "DROP TABLE IF EXISTS CONTACTSLOGS;";
    private static final String CREATE_LOGS_TABLE = "CREATE TABLE CONTACTSLOGS (USER_ID VARCHAR(20) NOT NULL,DATED VARCHAR	(200)NOT NULL,LOGGER VARCHAR	(50) NOT NULL,LEVEL VARCHAR	(10) NOT NULL, MESSAGE VARCHAR	(1000) NOT NULL);";
    private static final String FIND_CONTACTS_LOGS = "SELECT * FROM CONTACTSLOGS";

    private List<Log> printAllLogs(ResultSet resultSet) {

        List<Log> result = new ArrayList<>();
        try {
            java.sql.ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columsIdentified = resultSetMetaData.getColumnCount();
            System.out.println(
                    "\n =====================================================================================================");
            for (int i = 2; i <= columsIdentified; i++) {
                System.out.printf("%-30s\t", resultSetMetaData.getColumnName(i));
            }
            System.out.println(
                    "\n =====================================================================================================\n");
            if (resultSet.next()) {
                resultSet.beforeFirst();	
                while (resultSet.next()) {
                    for (int i = 2; i <= columsIdentified; i++) {
                        System.out.printf("%-2s\t", resultSet.getObject(i));
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
        return result;
    }

    private List<Log> findLogs() {
        Statement logsStatement;
        List<Log> result = new ArrayList<>();
        try {
            logsStatement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = printAllLogs(logsStatement.executeQuery(FIND_CONTACTS_LOGS));
            if (result.size() == 0) {
                logger.warn("There are no logs currently");
            }
            logsStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        List<Log> result1 = null;
        return result1;
    }

    private void deleteLogs() {
        try {
            Statement stmt1 = connection.createStatement();
            Statement stmt2 = connection.createStatement();
            stmt1.executeUpdate(DROP_LOGS_TABLE);
            stmt2.executeUpdate(CREATE_LOGS_TABLE);
            stmt1.close();
            stmt2.close();
            logger.info("All logs deleted successfuly");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            logger.error("Sorry, - something went wrong!", e);
        }
    }

    @Override
    public List<Log> findAllLogs() {
        return findLogs();
    }

    @Override
    public void deleteAllLogs() {
        deleteLogs();
    }

}
