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

        dataEntry contactInsert = new dataEntry();
        dataEdit contactUpdate = new dataEdit();
        dataSearch contactSearch = new dataSearch();

        Scanner input = new Scanner(System.in);
        String line = input.nextLine().trim();

        while (!line.equals("exit")) {
            switch (line.toLowerCase()) {
                case "help":
                    HelpMenu.helpOrMenu();
                    break;
                case "add":
                    contactInsert.insertData();
                    break; // #1 Create
                case "list":
                    contactSearch.findAllData();
                    break; // #2 Read
                case "name":
                    contactSearch.findNameData();
                    break; // #2 Read
                case "number":
                    contactSearch.findIdRecord();
                    break; // #2 Read
                case "update":
                    contactUpdate.updateData();
                    break; // #3 Update
                case "delete":
                    contactSearch.deleteData();
                    break; // #4 delete
                case "reset":
                    contactSearch.deleteAllContactsAndLogs();
                    break; // #4 delete contacts/logs
                case "logs":
                    contactSearch.findLogs();
                    break; // #2 Read logs
                case "count":
                    contactSearch.countAllContacts();
                    contactSearch.countAllClosing();
                    break; // #2 Read logs
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