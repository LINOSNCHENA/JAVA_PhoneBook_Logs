
DROP TABLE IF EXISTS CONTACTSBOOK;
CREATE TABLE CONTACTSBOOK
(
	id SERIAL PRIMARY KEY,
	pname TEXT NOT NULL,
	pnumber1 BIGINT NOT NULL,
	pnumber2 BIGINT,
	pstars INT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO CONTACTSLIST
	(pname,pnumber1,pnumber2,pstars)
VALUES('POLICE-EMERGENCE', 1992020, 22346, 78,90),
	('HOSPITAL-EMERGENCY', 2882020, 1235, 6789,7);
select *
from CONTACTSLIST;


DROP TABLE IF EXISTS CONTACTSLOGS;
CREATE TABLE CONTACTSLOGS
(
	USER_ID VARCHAR(20) NOT NULL,
	DATED VARCHAR	(200)NOT NULL,
	LOGGER VARCHAR	(50) NOT NULL,
	LEVEL VARCHAR	(10) NOT NULL,
	MESSAGE VARCHAR	(1000) NOT NULL
);

SELECT *
FROM CONTACTSLOGS