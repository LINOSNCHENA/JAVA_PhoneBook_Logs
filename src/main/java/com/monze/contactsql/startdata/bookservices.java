package com.monze.contactsql.startdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bookservices {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/presly";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Monze@2019";
    private static final String TABLE_NAME = "tblphone";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public bookservices() {
        // use as default user of database
        makeJDBCConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    private void makeJDBCConnection(String url, String username, String password) {
        connectDB(url, username, password);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void connectDB(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connection Successful!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void insertData(String pname, int pnumber1, int pnumber2) throws SQLException { /// CRUD1
        System.out.println("\n =============== CONTACTS DATA INSERTED =========================\n");

        String insertQueryStatement = "INSERT  INTO  " + TABLE_NAME + " (pname,pnumber1,pnumber2) values (?,?,?)";
        preparedStatement = connection.prepareStatement(insertQueryStatement);
        preparedStatement.setString(1, pname);
        preparedStatement.setInt(2, pnumber1);
        preparedStatement.setInt(3, pnumber2);

        preparedStatement.execute();

    }

    public ResultSet readData() throws SQLException { // CRUD 2

        System.out.println("\n =============== READ CONTACTS DATA FOUND =========================\n");
        String getQueryStatement = "SELECT * FROM " + TABLE_NAME + " ORDER BY id ASC";
        preparedStatement = connection.prepareStatement(getQueryStatement);
        // Execute the Query, and get a java ResultSet
        resultSet = preparedStatement.executeQuery();
        ResultSet rs = resultSet;
          while (rs.next()) {
            System.out.print("id    :");
            System.out.print(rs.getInt(1));
            System.out.print(" name  : ");
            System.out.print(rs.getString(2));
            System.out.print(" Mobile no. : ");
            System.out.print(rs.getString(3));
            System.out.print(" Office No. : ");
            System.out.println(rs.getString(4));
        }
        return resultSet;
    }

    public void updateData(int id, String pname, int pnumber1, int pnumber2) throws SQLException { /// CRUD3

        System.out.println("\n =============== UPDATE DATA =============================\n");

        String queryU = "UPDATE " + TABLE_NAME + " SET pname =?, pnumber1 = ?,pnumber2=? " + "WHERE id = ?";
        preparedStatement = connection.prepareStatement(queryU);
        preparedStatement.setString(1, pname);
        preparedStatement.setInt(2, pnumber1);
        preparedStatement.setInt(3, pnumber2);
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
    }

    public ResultSet deleteData() throws SQLException { // CRUD 4
        System.out.println("\n =============== DELETED # 4 DATA =============================\n");
        String query1 = "DELETE FROM " + TABLE_NAME + " WHERE id = 4";
        PreparedStatement st = connection.prepareStatement(query1);
        int rowsDeleted = st.executeUpdate();
        System.out.println(rowsDeleted + " Record-rows deleted");
        return resultSet;
    }

    public int numberOfContacts() throws SQLException { // CRUD B1
        System.out.println("\n =============== REPORTED BY COUNTER  ===================\n");
        String getQueryStatement = "SELECT COUNT(*) FROM " + TABLE_NAME;
        preparedStatement = connection.prepareStatement(getQueryStatement);
        // Execute the Query, and get a java ResultSet
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    public boolean hasRecords() throws SQLException {
        System.out.println("\n =============== SENSUS ON DATA noSQL ===========================\n");

        int x = numberOfContacts();
        System.out.println("Total records count :" + x);
        resultSet = readData();
        return resultSet.next();
    }

    public ResultSet findSimilarData(String pnameX) throws SQLException { // CRUD B2 -LIKE

        System.out.println("\n =============== SIMILAR DATA FOUND =============================\n");

        String query1 = "SELECT * FROM " + TABLE_NAME + " WHERE pname LIKE '%" + pnameX + "%'";
        PreparedStatement st = connection.prepareStatement(query1);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            System.out.print("id    :");
            System.out.print(rs.getInt(1));
            System.out.print(" name  : ");
            System.out.print(rs.getString(2));
            System.out.print(" Mobile no. : ");
            System.out.print(rs.getString(3));
            System.out.print(" Office No. : ");
            System.out.println(rs.getString(4));
        }

        return resultSet;

    }

    public ResultSet findExactData(String pnameX) throws SQLException { // CRUD B3 -EXACT
        System.out.println("\n =============== EXACTLY DATA FOUND =============================\n");

        String query1 = "SELECT * FROM " + TABLE_NAME + " WHERE  pname ='" + pnameX + "'";
        PreparedStatement st = connection.prepareStatement(query1);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            System.out.print("id    :");
            System.out.print(rs.getInt(1));
            System.out.print(" name  : ");
            System.out.print(rs.getString(2));
            System.out.print(" Mobile no. : ");
            System.out.print(rs.getString(3));
            System.out.print(" Office No. : ");
            System.out.println(rs.getString(4));
        }
        return resultSet;

    }

    public ResultSet searchNameResults(String pname) throws SQLException { // CRUD B4
        resultSet = findExactData(pname);
        System.out.println("\n =============== SEARCH BY NAME RESULTS noSQL===================\n");
        System.out.println(resultSet);
        System.out.println("\n =============== SEARCH BY NAME RESULTS noSQL===================\n");
        return resultSet;
    }

}