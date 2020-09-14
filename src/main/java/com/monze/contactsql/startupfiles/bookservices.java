package com.monze.contactsql.startupfiles;

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////// THREE
    public bookservices() {
        // Use defaults if no connection information provided
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
            // log("MySQL Connection Failed!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////// CRUD
    public void insertData(String pname, int pnumber1, int pnumber2) throws SQLException { /// CRUD1

        String insertQueryStatement = "INSERT  INTO  " + TABLE_NAME + " (pname,pnumber1,pnumber2) values (?,?,?)";
        preparedStatement = connection.prepareStatement(insertQueryStatement);
        preparedStatement.setString(1, pname);
        preparedStatement.setInt(2, pnumber1);
        preparedStatement.setInt(3, pnumber2);

        preparedStatement.execute();

    }

    public void updateData(int id, String pname, int pnumber1, int pnumber2) throws SQLException { /// CRUD2

        System.out.println("\n =============== UPDATE DATA =============================\n");

        String queryU = "UPDATE " + TABLE_NAME + " SET pname =?, pnumber1 = ?,pnumber2=? " + "WHERE id = ?";
        preparedStatement = connection.prepareStatement(queryU);
        preparedStatement.setString(1, pname);
        preparedStatement.setInt(2, pnumber1);
        preparedStatement.setInt(3, pnumber1);
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
    }

    public ResultSet readData() throws SQLException { // CRUD 3

        System.out.println("\n =============== CONTACTS DATA =============================\n");
        String getQueryStatement = "SELECT * FROM " + TABLE_NAME + " ORDER BY id ASC";
        preparedStatement = connection.prepareStatement(getQueryStatement);
        // Execute the Query, and get a java ResultSet
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet deleteData() throws SQLException { // CRUD 4
        String query1 = "DELETE FROM " + TABLE_NAME + " WHERE id = 12";
        PreparedStatement st = connection.prepareStatement(query1);
        int rowsDeleted = st.executeUpdate();
        System.out.println(rowsDeleted + " Record-rows deleted");
        return resultSet;
    }

    public ResultSet showData(String pnameX) throws SQLException { // CRUD 5

        System.out.println("\n =============== SHOW DATA =============================\n");
        
        String query1 = "SELECT * FROM " + TABLE_NAME + " WHERE pname LIKE '%" + pnameX + "%'";
        PreparedStatement st = connection.prepareStatement(query1);
        ResultSet rs = st.executeQuery();
      //  System.out.println("\n Opened database successfully.\n Loading Data .......\n");
        while (rs.next()) {
            System.out.print(" ");
            System.out.print(rs.getInt(1));
            System.out.print(" : ");
            System.out.print(rs.getString(2));
            System.out.print(" : ");
            System.out.print(rs.getString(3));
            System.out.print(" : ");
            System.out.println(rs.getString(4));
        }
     //   System.out.println("\n =============== SHOW DATA 2=============================\n");
 
        return resultSet;

    }

   public int numberOfContacts() throws SQLException { // CRUD 4
        System.out.println("\n =============== COUNTER TOTAL ===================\n");
        String getQueryStatement = "SELECT COUNT(*) FROM " + TABLE_NAME;
        preparedStatement = connection.prepareStatement(getQueryStatement);
        // Execute the Query, and get a java ResultSet
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
                  return resultSet.getInt(1);
        }
        return 0;
    }

    
    public ResultSet findData( String pnameX) throws SQLException { // CRUD 5
        // String pnameX
     
         System.out.println("\n =============== FIND START ============================\n");
         String query1 = "SELECT * FROM " + TABLE_NAME + " WHERE  pname ='"+pnameX+"'";
         PreparedStatement st = connection.prepareStatement(query1);
         ResultSet rs = st.executeQuery();
       //  System.out.println("\n Opened database successfully.\n Loading Data .......\n");
         while (rs.next()) {
             System.out.print(" id=");
             System.out.print(rs.getInt(1));
             System.out.print(" name=: ");
             System.out.print(rs.getString(2));
             System.out.print("mb= : ");
             System.out.print(rs.getString(3));
             System.out.print("off : ");
             System.out.println(rs.getString(4));
         }
     //    System.out.println("\n =============== FIND DATA 2=============================\n");
         return resultSet;
 
     }
     ///////////////////////////////////////////////////////////////////////////////////////////
  

       public boolean hasRecords() throws SQLException {
        resultSet = readData();
        return resultSet.next();
    }
    public ResultSet testSerach() throws SQLException {
        resultSet = showData("dd");
        return resultSet;
    }
    //////////////////////////////////////////////////////////////////////////////////////////

}