package com.apiRepos;

import java.util.List;

import com.model.Contact;

public interface contactsDao {

    void insertNewContact(String pname, int pnumber1, int pnumber2);

    List<Contact> findAllContacts();
    Contact findContactById(Integer id);

    Contact findContactByName(String pname);

    void updateOneContact(Integer id, String pname, int pnumber1, int pnumber2);

    void deleteOneContact(Integer id);

    void deleteAllContacts();

    void formatContactsTable();
}
