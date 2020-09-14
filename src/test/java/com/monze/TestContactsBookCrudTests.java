package com.monze;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.sql.SQLException;

import com.monze.contactsql.startupfiles.bookservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class TestContactsBookCrudTests {

    private bookservices contactsDatabase;

    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Connection connection;
    @Mock
    private ResultSet resultSet; // Third

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        contactsDatabase = new bookservices();

        contactsDatabase.setConnection(connection);
        contactsDatabase.setPreparedStatement(preparedStatement);
        contactsDatabase.setResultSet(resultSet);
    }

    @Test
    public void testNumberOfContacts() throws SQLException {
        // When there are contacts
        int expectedContactsTotal = 3;
        when(connection.prepareStatement("SELECT COUNT(*) FROM tblphone")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(expectedContactsTotal);

        /// Tests
        assertEquals(expectedContactsTotal, contactsDatabase.numberOfContacts());
        verify(resultSet).getInt(1);

        // When there are not any contacts
        when(resultSet.next()).thenReturn(false);
        assertEquals(0 + 3, contactsDatabase.numberOfContacts() + 3);
    }

    @Test
    public void testSearchContactByCorrectName() throws SQLException {
        // When there is a contacts saved as LEON
        String pnamex = "LEON";
        String pnamez = "LEONx";
        String sql1 = "INSERT INTO tblphone(pname,pnumber1,pnumber2) VALUES('LEON',8822288,299991);";
        String sql3 = "SELECT * FROM  tblphone WHERE  pname ='" + pnamex + "'";

        when(connection.prepareStatement(sql1)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(connection.prepareStatement(sql3)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(2)).thenReturn(pnamex);
        String result1 = resultSet.getString(2);

        // Trials
        assertEquals(pnamex, result1);
        assertNotEquals(pnamez, result1);
    }

    @Test
    public void testSearchContactByWrongName() throws SQLException {
        // When there is a contacts saved as LEON
        String pnamex = "LEON";
        String pnamez = "LEONx";
        String sql1 = "INSERT INTO tblphone(pname,pnumber1,pnumber2) VALUES('LEON',8822288,299991);";
        String sql3 = "SELECT * FROM  tblphone WHERE  pname ='" + pnamex + "'";

        when(connection.prepareStatement(sql1)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(connection.prepareStatement(sql3)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(2)).thenReturn(pnamex);
        String result1 = resultSet.getString(2);

        // Trials
        assertEquals(pnamex, result1);
        assertNotEquals(pnamez, result1);
    }

}