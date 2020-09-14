package com.monze.contactsql.startdata;

import java.sql.ResultSet;
import java.sql.SQLException;

public class bookDefaultData {

    public static void bookstarter() {
        bookservices phoneBook = new bookservices();
        try {
            System.out.println(phoneBook.numberOfContacts()); // Thired call
            if (!phoneBook.hasRecords()) { // creates record #1 YES/NO
                phoneBook.insertData("NIKOLAS", 777777, 67777);
                phoneBook.insertData("LORENA", 666665, 666667);
                phoneBook.insertData("LEON", 5555555, 55557);
                phoneBook.insertData("CATHERINE", 44444445, 7444444);
                phoneBook.insertData("NCHENA", 3333335, 3333338);

                phoneBook.insertData("NIKOLAS", 777777, 67777);
                phoneBook.insertData("LORENA", 666665, 666667);
                phoneBook.insertData("LEON", 5555555, 55557);
                phoneBook.insertData("CATHERINE", 44444445, 7444444);
                phoneBook.insertData("NCHENA", 3333335, 3333338);
            }
            if (!phoneBook.hasRecords()) { // updates record #2 YES/NO
                phoneBook.updateData(10, "NANCY", 987654321, 22222222);
                phoneBook.updateData(11, "DIANA", 22222222, 2222222);
                phoneBook.updateData(12, "HENRY", 22222222, 2222222);
                phoneBook.updateData(13, "COLLIN", 2222222, 2222222);
                phoneBook.updateData(14, "NCHENA", 22222222, 987654321);
            }
            ResultSet rs = phoneBook.readData(); // reads record #3
            // Let's iterate through the resultantant ResultSet
            while (rs.next()) {

                String pname = rs.getString("pname");
                int pnumber1 = rs.getInt("pnumber1");
                int pnumber2 = rs.getInt("pnumber2");
                int id = rs.getInt("id");
                System.out.printf("%s,%s,%s, %s\n", id, pname, pnumber1, pnumber2);
            }

            System.out.println(phoneBook.deleteData()); // Deletes record #4

            System.out.println(phoneBook.searchNameResults("NANCY")); // Thired call
            System.out.println(phoneBook.findSimilarData("L")); // Thired call
            System.out.println(phoneBook.findExactData("LEON")); // Thired call
            
            System.out.println(phoneBook.numberOfContacts()); // Thired call
    
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
