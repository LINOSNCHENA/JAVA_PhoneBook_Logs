package com;

import java.util.Scanner;
import java.util.logging.Logger;

import com.apiRepos.servicesApi.dataEdit;
import com.apiRepos.servicesApi.dataEntry;
import com.apiRepos.servicesApi.dataSearch;
import com.model.HelpMenu;

public class setUp {
    static Logger logSetUp = Logger.getLogger(setUp.class.getName());

    public static void main(String[] args) {

        com.model.HelpMenu.helpOrMenu();
        dataEntry phoneBookInsert = new dataEntry();
        dataEdit phoneBookUpdate = new dataEdit();
        dataSearch phoneBookSearch = new dataSearch();

        Scanner input = new Scanner(System.in);
        String line = input.nextLine().trim();

        while (!line.equals("exit")) {
            switch (line.toLowerCase()) {
                case "help":
                    HelpMenu.helpOrMenu();
                    break;
                case "add":
                    phoneBookInsert.insertData();
                    break; // CRUD #1 Create
                case "update":
                    phoneBookUpdate.updateData();
                    break; // CRUD #3 Update
                case "list":
                    phoneBookSearch.findAllData();
                    break; // CRUD #2 Read
                case "name":
                    phoneBookSearch.findNameData();
                    break; // CRUD #2 Read
                case "number":
                    phoneBookSearch.findOneData();
                    break; // CRUD #2 Read
                case "delete":
                    phoneBookSearch.deleteData();
                    break; // CRUD #4 delete
                case "reset":
                    phoneBookSearch.formatLogs();
                    phoneBookSearch.formatContacts();
                    break; // CRUD #4 delete contacts/logs
                case "logs":
                    phoneBookSearch.findLogs();
                    break; // CRUD #2 Read logs
                default:
                    logSetUp.info(":   Please select an available command word >>> ");
                    break;
            }
            line = input.nextLine().trim();
            logSetUp.info("Task computation complete >>> \n");

        }
        input.close();

        System.out.println("\n !================= PHONE BOOK EXITED AND CLOSED ====================!\n");
    }
}