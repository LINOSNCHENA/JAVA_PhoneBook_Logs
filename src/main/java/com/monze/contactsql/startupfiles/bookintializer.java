package com.monze.contactsql.startupfiles;

import java.sql.ResultSet;
import java.sql.SQLException;

public class bookintializer {

   public static void bookstarter() {
        bookservices phoneBook = new bookservices();
        try {
            if (phoneBook.hasRecords()) { // first call
                phoneBook.insertData("NIKOLAS", 777777, 67777);
                phoneBook.insertData("LORENA", 666665, 666667);
                phoneBook.insertData("LEON", 5555555, 55557);
                phoneBook.insertData("CATHERINE", 44444445, 7444444);
                phoneBook.insertData("NCHENA", 3333335, 3333338);
            }
            if (phoneBook.hasRecords()) { // first call
                phoneBook.updateData(52, "NANCY", 444444, 522222222);
                phoneBook.updateData(53, "DIANA", 444444444, 522222222);
                phoneBook.updateData(54, "HENRY", 222222, 33333333);
                phoneBook.updateData(55, "COLLIN", 6666667, 5666666);
                phoneBook.updateData(51, "NCHENA", 6666667, 22222222);
            }
            ResultSet rs = phoneBook.readData(); // second call
            // Let's iterate through the java ResultSet
            System.out.print("============================START================");
            while (rs.next()) {

                String pname = rs.getString("pname");
                int pnumber1 = rs.getInt("pnumber1");
                int pnumber2 = rs.getInt("pnumber2");
                int id = rs.getInt("id");
                System.out.printf("%s,%s,%s, %s\n", id, pname, pnumber1, pnumber2);
            }
            System.out.print("===================== END =======================");
        
            System.out.println(phoneBook.deleteData()); // Thired call
            System.out.println(phoneBook.showData("fire Emmergency")); // Thired call
            System.out.println(phoneBook.findData("LEON")); // Thired call
            System.out.println(phoneBook.numberOfContacts()); // Thired call
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
