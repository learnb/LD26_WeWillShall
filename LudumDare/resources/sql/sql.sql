--Contains schema to current GAMEDATA database
CREATE TABLE RESOURCES
(
RESOURCE_ID VARCHAR(30) PRIMARY KEY,
TYPE VARCHAR(30),
PATH VARCHAR(255)
);

CREATE TABLE GLOBALS
(
KEY VARCHAR(255) PRIMARY KEY,
VALUE VARCHAR(255)
);