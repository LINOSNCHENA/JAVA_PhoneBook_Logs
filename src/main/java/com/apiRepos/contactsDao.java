package com.apiRepos;

import java.util.List;

import com.model.Contact;

public interface contactsDao {

    void insertNewContact(String name, int mobile, int office, int stars);

    List<Contact> findAllContacts();

    Contact findContactById(Integer id);

    Contact findContactByName(String name);

    void updateOneContact(Integer id, String name, int mobile, int office,int stars);

    int countAllContacts();
    void deleteOneContact(Integer id);

    void deleteAllContacts();

}
