package com.monze.contactsql.dbmgmt;

public class contactsManual {
  public static void helpOrMenu() {
    String adviceMenu = "-------------------------------------------------------------\n"
        + "       WELCOME TO MY POSTGRESQL PHONEBOOK                \n"
        + " Select one action below and then type its command below\n"
        + "=============================================================\n" +

        "menu   - to lists all valid commands available\n" + "format - to erase all your current contacts\n"
        + "help   - to display the menu for commands \n\n" +

        "delete - to erase a contact from the phone book\n"
        + "list   - to lists all saved contacts sorted by date of entry\n"
        + "add    - to saves a new contact entry into the phone book\n\n" +

        "show   - to search for a contact by the contact name\n"
        + "find   - to search for a contact by the contact number\n"
        + "update - to edit the phone number of  existing contact\n\n" +

        "exit   - STOP using the Phone Book application console\n" +

        "--------------------------------------------------------------\n\n"
        + " To continue, please enter an action command here >>>  ";
    System.out.print(adviceMenu);
  }

}
