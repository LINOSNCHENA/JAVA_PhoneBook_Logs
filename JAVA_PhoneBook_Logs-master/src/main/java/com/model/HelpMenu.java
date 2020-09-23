package com.model;

public class HelpMenu {

  public static void helpOrMenu() {

    String adviceMenu = "-------------------------------------------------------------\n"
        + "       WELCOME TO MY PHONEBOOK APPLICATION               \n"
        + " Select one action below and then type its command below\n"
        + "=============================================================\n" +

        "HELP   - to lists all available valid commands on this application \n\n"

       
       
        + "ADD    - to insert a new contact entry into the phone book\n" 
        + "LIST   - to lists all saved contacts sorted by date of entry\n"
        + "DELETE - to erase a specified contact from the phone book\n"+

        "NAME   - to search for a contact by the contact name\n"
        + "NUMBER   - to search for a contact by the contact ID-number\n"
        + "UPDATE - to edit the contact details of an existing contact\n\n"

        + "RESET1   - Deletes all contacts records and restores factory defaults\n"
        + "RESET2   - Deletes all logs datails in the log database tables\n\n"

        + "LOGS   - To view details of all log data manipulations recordss\n"
        + "EXIT   - End the usage of this contacts book application console\n" +

        "--------------------------------------------------------------\n\n"
        + " To continue, please enter an action command here >>>  ";

    System.out.print(adviceMenu);
  }

}
