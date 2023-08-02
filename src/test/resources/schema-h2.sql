CREATE TABLE accounts_tb (
  accountID int NOT NULL,
  accountKey varchar(200) DEFAULT NULL,
  accountSize int NOT NULL,
  accountType varchar(30) NOT NULL,
  created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (accountID)
); 

