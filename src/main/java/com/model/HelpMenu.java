package com.model;

public class HelpMenu {

  public static void helpOrMenu() {

    String adviceMenu = "-------------------------------------------------------------\n"
        + "       WELCOME TO MY PHONEBOOK APPLICATION               \n"
        + " Select one action below and then type its command below\n"
        + "=============================================================\n" +

        "HELP   - to lists all valid commands available in this application \n"

        + "DELETE - to erase a contact from the phone book\n"
        + "LIST   - to lists all saved contacts sorted by date of entry\n"
        + "ADD    - to saves a new contact entry into the phone book\n\n" +

        "NAME   - to search for a contact by the contact name\n"
        + "NUMBER   - to search for a contact by the contact number\n"
        + "UPDATE - to edit the phone number of  existing contact\n\n"

        + "RESET1   - Deletes all contacts records and restores factory defaults\n"
        + "RESET2   - Deletes all logs datails & records and restores factory defaults\n\n"

        + "ADMIN - to view past log data manipulations of contacts\n"
        + "EXIT   - STOP using the Phone Book application console\n" +

        "--------------------------------------------------------------\n\n"
        + " To continue, please enter an action command here >>>  ";

    System.out.print(adviceMenu);
  }

}
