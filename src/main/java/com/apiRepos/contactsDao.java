package com.apiRepos;

import java.util.List;

import com.model.Contact;

public interface contactsDao {

    void insertNewContact(String nameX, int mobileX, int officeX, int starX);

    List<Contact> findAllContacts();

    Contact findContactById(Integer id);

    Contact findContactByName(String nameX);

    void updateOneContact(Integer id, String nameX, int mobileX, int officeX,int starX);

    void deleteOneContact(Integer id);

    void deleteAllContacts();

}
