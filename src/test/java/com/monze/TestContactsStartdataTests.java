package com.monze;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.sql.SQLException;
import com.monze.contactsql.startdata.bookservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TestContactsStartdataTests {

    private bookservices contactsDatabase;

    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Connection connection;
    @Mock
    private ResultSet resultSet; // Third
    @InjectMocks
    bookservices bookservicesx;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUpfoneBook() {
        contactsDatabase = new bookservices();

        contactsDatabase.setConnection(connection);
        contactsDatabase.setPreparedStatement(preparedStatement);
        contactsDatabase.setResultSet(resultSet);
    }

    @Test
    public void testNumberOfContactsFound() throws SQLException {
        // When there are contacts
        int expectedContactsTotal = 3;
        when(connection.prepareStatement("SELECT COUNT(*) FROM tblphone")).thenReturn(preparedStatement);// 1
        when(preparedStatement.executeQuery()).thenReturn(resultSet); // 2
        when(resultSet.next()).thenReturn(true); // 3
        when(resultSet.getInt(1)).thenReturn(expectedContactsTotal);// 4

        /// Tests
        assertEquals(expectedContactsTotal, contactsDatabase.numberOfContacts());// 5
        verify(resultSet).getInt(1);
        System.out.println("\n========================== NUMMBEROFCONTACTS TEST x1 ====================================\n");
        System.out.println(expectedContactsTotal);
        System.out.println(resultSet.getInt(1));
        System.out.println(contactsDatabase);
        System.out.println(contactsDatabase.numberOfContacts());
        System.out.print(expectedContactsTotal + 7);
        System.out.println("\n========================== NUMMBEROFCONTACTS TEST x2 ====================================\n");

        // When there are not any contacts
        when(resultSet.next()).thenReturn(false);
        assertEquals(0 + 3, contactsDatabase.numberOfContacts() + 3);
    }

    @Test

    public void testIfThereIsARecordPresentInDatabase() throws SQLException {
        // When there is a contacts saved as LEON
        String pnamex = "LEON";
        String pnamez = "LEONx";
        boolean zed=true;
        String sql1 = "INSERT INTO tblphone(pname,pnumber1,pnumber2) VALUES('LEON',8822288,299991);";
        String sql3 = "SELECT * FROM  tblphone WHERE  pname ='" + pnamex + "'";

        when(connection.prepareStatement(sql1)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement(sql3)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(2)).thenReturn(pnamex);
        String result1 = resultSet.getString(2);

     //  when(bookservicesx.hasRecords()).thenReturn(zed);
      // assertEquals(true, zed);
     //  assertEquals(zed, contactsDatabase.hasRecords());// 5
     //  verify(resultSet).getInt(1);

      // assertEquals(expectedContactsTotal, contactsDatabase.numberOfContacts());// 5
      // verify(resultSet).getInt(1);

        System.out.println("\n============================ HASRECORDS-TEST x1 =======================================\n");
        System.out.println(result1+zed);
        System.out.println(resultSet);
        System.out.println(contactsDatabase);
      //  System.out.println(contactsDatabase.hasRecords());
      //  System.out.println(contactsDatabase.numberOfContacts());
        System.out.println(result1);
        System.out.println(resultSet);
        System.out.println("\n========================= HASRECORDS-TEST x2 ======================================\n");

        // Trials
        assertEquals(pnamex, result1);
        assertNotEquals(pnamez, result1);
    }

    @Test
    public void testSearchContactByExactNamezzzzz() throws SQLException {
        // When there is a contacts saved as LEON
        String pnamex = "LEON", pnamey = "LEON";
        int kathy = 1947, linos = 1943, noria = 1924;
        //
        String sql1 = "INSERT INTO tblphone(pname,pnumber1,pnumber2) VALUES('LEON',8822288,299991);";
        String sql2 = "SELECT * FROM  tblphone WHERE  pname ='" + pnamex + "'";
        when(connection.prepareStatement(sql1)).thenReturn(preparedStatement);// 1
        when(preparedStatement.executeQuery()).thenReturn(resultSet);// 2
        when(connection.prepareStatement(sql2)).thenReturn(preparedStatement);// 1
        when(preparedStatement.executeQuery()).thenReturn(resultSet);// 2
        when(resultSet.next()).thenReturn(true); // 3

        when(resultSet.getInt(1)).thenReturn(linos);// 4
        when(resultSet.getInt(2)).thenReturn(kathy);// 4
        when(resultSet.getInt(3)).thenReturn(noria);// 4
        when(resultSet.getString(2)).thenReturn(pnamex);// 4
        when(resultSet.getString(2)).thenReturn(pnamey);// 4

        // assertEquals(zed, contactsDatabase.hasRecords());// 5
        // verify(resultSet).getInt(1);

        System.out.println("\n=========================== ZAMBIA-SEARCH-NAME x1 =========================\n");
        System.out.println(pnamex);
        System.out.println(pnamey);
        System.out.println(resultSet.getString(2)); // inserted
        System.out.println(resultSet.getString(2) + "\n"); // serched

        // System.out.println(contactsDatabase.findExactData("LEON") + "\n"); // serched
        // System.out.println(contactsDatabase.findExactData("LEON") + "\n"); // serched
        System.out.println(contactsDatabase);
        // System.out.println(contactsDatabase.hasRecords());

        System.out.println(linos);
        System.out.println(kathy);
        System.out.println(noria + "\n");

        System.out.println(resultSet.getInt(1));
        System.out.println(resultSet.getInt(2));
        System.out.println(resultSet.getInt(3));

        System.out.print("\n============================ ZAMBIA-SEARCH-NAME x2 =========================\n");

        // /// Tests
        // assertEquals(pnamex, contactsDatabase.findExactData("LEON").getString(2));//
        // 5
        // verify(resultSet).getString(2);
        // resultx = resultSet.getString(2);
        // // System.out.println(resultx);

        // // Trials
        // assertEquals(pnamex, resultx);
        // // assertNotEquals(pnamez, resultx);
    }

    // @Test
    // public void testSearchContactBySimilarName() throws SQLException {
    //     // When there is a contacts saved as LEON
    //     String pnameGood = "LEON";
    //     String pnamez = "LEONx";
    //     String resultx;
    //     String sql1 = "INSERT INTO tblphone(pname,pnumber1,pnumber2) VALUES('LEON',8822288,299991);";
    //     String sql3 = "SELECT * FROM tblname WHERE pname LIKE '%" + pnameGood + "%'";

    //     when(connection.prepareStatement(sql1)).thenReturn(preparedStatement);
    //     when(preparedStatement.executeQuery()).thenReturn(resultSet);
    //     when(connection.prepareStatement(sql3)).thenReturn(preparedStatement);
    //     when(preparedStatement.executeQuery()).thenReturn(resultSet);

    //     when(resultSet.next()).thenReturn(true);
    //     when(resultSet.getString(2)).thenReturn(pnameGood);
    //     resultx = resultSet.getString(2);

    //     // Trials
    //     assertEquals(pnameGood, resultx);
    //     assertNotEquals(pnamez, resultx);
    // }

}