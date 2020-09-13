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
import static org.mockito.Mockito.*;

public class phoneContactsCrudTests {

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
    public void testContactNameSearch() throws SQLException {
        // When there are contacts
        String expectedContacts = "LEON";
        String expectedContacts1 = "LEONx";
        String query1 = "SELECT * FROM tblphone WHERE  pname ='"+expectedContacts1+"'";
        when(connection.prepareStatement(query1)).thenReturn(preparedStatement);
       
   
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(2)).thenReturn(expectedContacts);

        /// Tests
        assertEquals(expectedContacts, contactsDatabase.findData("LEON"));
        verify(resultSet).getString(2);
          // When there are not any contacts
        when(resultSet.next()).thenReturn(false);
        assertEquals(expectedContacts, contactsDatabase.findData("LEONx"));
    }

}