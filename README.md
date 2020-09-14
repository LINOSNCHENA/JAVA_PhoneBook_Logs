
# PHONE BOOK COMMAND LINE APPLICATION

## INSTALLATION REQUIREMENTS
=============================
1. java 8 installation
2. Postgresql Or MySql database installation
3. Maven installation

## INSTALLATION CHECKING
=========================

```
echo %MAVEN_HOME%
echo %M2_HOME%
echo %CLASSPATH%

```

## COMMANDS TO EXECUTE BUILD
==============================

Update USERNAME for the database table/database
Update PASSWORD for the database table/database in file in resources/database.properties

```
mvn clean install -X
mvn test

```


## COMMANDS TO RUN IN PROMPT
==============================
java -cp target/focusphone-1.0-SNAPSHOT.jar com.monze.App

## SERVICEES AVAILABLE ON PHONE BOOK
======================================
Available options to manipulate phonebook using console application.

  menu   - to lists all valid commands available
  format - to erase all your current contacts
  help   - to display the menu for commands 

  delete - to erase a contact from the phone book
  list   - to lists all saved contacts sorted by date of entry
  add    - to saves a new contact entry into the phone book
    
  show   - to search for a contact by the contact name
  find   - to search for a contact by the contact number
  update - to edit the phone number of  existing contact

  exit  - to end the console application
    

# PHONE BOOK - INTERFACE UX 
=============================
Here are screen-shots from the MPC compututation results

![ Muntu App SMS # 1 ](https://github.com/LINOSNCHENA/PhoneBookApp/blob/master/ux/page%20(1).png)
![ Muntu App SMS # 2 ](https://github.com/LINOSNCHENA/PhoneBookApp/blob/master/ux/page%20(2).png)

![ Muntu App SMS # 3 ](https://github.com/LINOSNCHENA/PhoneBookApp/blob/master/ux/page%20(3).png)
![ Muntu App SMS # 4 ](https://github.com/LINOSNCHENA/PhoneBookApp/blob/master/ux/page%20(4).png)
