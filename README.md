# CONTACTS LIST COMMAND LINE APPLICATION

## 1. ENVIRONMENTAL REQUIREMENTS

1. Java 8 installation
2. Postgresql Or MySql database installation
3. Maven installation
4. IDE(VSCode tested), or CMD/Bash terminal

## 2. PROJECT INSTALLATION CHECKING


```
echo %JAVA_HOME%
echo %MAVEN_HOME%
echo %M2_HOME%
echo %CLASSPATH%

```

## 3. PROCEDURE AND COMMANDS TO EXECUTE BUILD


Update USERNAME/PASSWORD for the database table\
Database schema  : PRESLY\
Database table1  : CONTACTSLIST\
Database table2  : CONTACTSLOGS

Contacts database setting is found in folder main/java/dbase/dbConfig.java\
Logs database setting is found in folder main/resources/log4j.properties


```
mvn clean install -X
mvn test

```


## 4. CMD COMMAND TO RUN AFTER EXECUTING A SUCCESSFUL BUILT


java -cp target/focusphone-1.0-SNAPSHOT.jar com.setUp



## 5. SERVICES AVAILABLE ON THE APP



Available options to manipulate the phonebook using console application.

"HELP   - to lists all valid commands available in this application "

"ADD    - to saves a new contact entry into the phone book" \
"LIST   - to lists all saved contacts sorted by date of entry"\
"DELETE - to erase a contact from the phone book"

"NAME   - to search for a contact by the contact name"\
"NUMBER   - to search for a contact by the contact number"\
"UPDATE - to edit the phone number of  existing contact"

"RESET   - Deletes all database records and restores factory defaults"\
"LOGS - to view past log data manipulations of contacts"\
"EXIT   - STOP using the Phone Book application console" 

"--------------------------------------------------------------"

" To continue, please enter an action command here >>>  ";
    
# 6. APPLICATION CONSOLE- INTERFACE UX 


Here are screen-shots from the output of application's console

![ Muntu App SMS # 1 ](https://github.com/LINOSNCHENA/JAVA_PhoneBook_Logs/blob/master/ux/page%20(1).png)
![ Muntu App SMS # 2 ](https://github.com/LINOSNCHENA/JAVA_PhoneBook_Logs/blob/master/ux/page%20(2).png)

![ Muntu App SMS # 3 ](https://github.com/LINOSNCHENA/JAVA_PhoneBook_Logs/blob/master/ux/page%20(3).png)
![ Muntu App SMS # 4 ](https://github.com/LINOSNCHENA/JAVA_PhoneBook_Logs/blob/master/ux/page%20(4).png)
