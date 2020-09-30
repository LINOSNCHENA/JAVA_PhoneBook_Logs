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

public class logBookImplemented implements logBook {

    final static Logger logger = Logger.getLogger(logBook.class);

    private final Connection connection = connFactory.getConnection(dbConfig.url, dbConfig.user, dbConfig.passwd);

    private static final String DROP_LOGS_TABLE = "DROP TABLE IF EXISTS CONTACTSLOGS;";
    private static final String CREATE_LOGS_TABLE = "CREATE TABLE CONTACTSLOGS (USER_ID VARCHAR(20) NOT NULL,DATED VARCHAR	(200)NOT NULL,LOGGER VARCHAR	(50) NOT NULL,LEVEL VARCHAR	(10) NOT NULL, MESSAGE VARCHAR	(1000) NOT NULL);";
    private static final String FIND_CONTACTS_LOGS = "SELECT * FROM CONTACTSLOGS";

    private List<Log> getAllLogsFromResultLogs(ResultSet resultSet) {

        List<Log> result = new ArrayList<>();
        try {

            while (resultSet.next()) {
                System.out.print(" Level : ");
                System.out.print(resultSet.getString(4));
                System.out.print(" Dated : ");
                System.out.print(resultSet.getString(2));
                System.out.print(" LogFile : ");
                System.out.print(resultSet.getString(3));
                System.out.print(" Message : ");
                System.out.println(resultSet.getString(5));
            }
            logger.info("Amin has performed control tasks");

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        return result;
    }

    private List<Log> findLogs() {
        Statement statement;
        List<Log> result = new ArrayList<>();

        try {
            statement = connection.createStatement();
            result = getAllLogsFromResultLogs(statement.executeQuery(FIND_CONTACTS_LOGS));
            if (result.size() == 0) {

                logger.warn("There are no logs currently");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Sorry, - something went wrong!", e);
        }
        List<Log> result1 = null;

        return result1;
    }

    private void formatLogs() {
        try {
            Statement stmt1 = connection.createStatement();
            Statement stmt2 = connection.createStatement();
            stmt1.executeUpdate(DROP_LOGS_TABLE);
            stmt2.executeUpdate(CREATE_LOGS_TABLE);
            stmt1.close();
            stmt2.close();
            logger.info("All logs records have been formated successfuly");
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
    public void formatLogsTable() {
        formatLogs();
    }

}
