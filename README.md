
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
echo %JAVA_HOME%

```

## COMMANDS TO EXECUTE BUILD
==============================

Update USERNAME/PASSWORD for the database table\
Database table/database is found in file located in resources/database.properties

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

"HELP   - to lists all valid commands available in this application "

"DELETE - to erase a contact from the phone book\n"
"LIST   - to lists all saved contacts sorted by date of entry"
"ADD    - to saves a new contact entry into the phone book" +

"NAME   - to search for a contact by the contact name"
"NUMBER   - to search for a contact by the contact number"
"UPDATE - to edit the phone number of  existing contact"

"RESET1   - Deletes all contacts records and restores factory defaults"
"RESET2   - Deletes all logs datails & records and restores factory defaults"

"ADMIN - to view past log data manipulations of contacts"
"EXIT   - STOP using the Phone Book application console" +

"--------------------------------------------------------------"

" To continue, please enter an action command here >>>  ";
    

# PHONE BOOK - C/C++ PROGRAMMING 
=============================

1. Task 1&2
1. Task 3&4
1. Task 5&6
1. Task 7&8


# PHONE BOOK - INTERFACE UX 
=============================

Here are screen-shots from the MPC compututation results

![ Muntu App SMS # 1 ](https://github.com/LINOSNCHENA/PhoneBookApp/blob/master/ux/page%20(1).png)
![ Muntu App SMS # 2 ](https://github.com/LINOSNCHENA/PhoneBookApp/blob/master/ux/page%20(2).png)

![ Muntu App SMS # 3 ](https://github.com/LINOSNCHENA/PhoneBookApp/blob/master/ux/page%20(3).png)
![ Muntu App SMS # 4 ](https://github.com/LINOSNCHENA/PhoneBookApp/blob/master/ux/page%20(4).png)
