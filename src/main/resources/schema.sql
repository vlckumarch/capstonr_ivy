DROP TABLE IF EXISTS lab;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS author;

CREATE TABLE category (
    id INT IDENTITY(1,1) PRIMARY KEY, 
    name NVARCHAR(35));

CREATE TABLE author (
    id INT IDENTITY(1,1) PRIMARY KEY, 
    first_name NVARCHAR(30),
    last_name NVARCHAR(40));

CREATE TABLE lab (
    id INT IDENTITY(1,1) PRIMARY KEY, 
    name NVARCHAR(50),
    description NVARCHAR(100),
    category_id INT FOREIGN KEY REFERENCES category(id),
    author_id INT FOREIGN KEY REFERENCES author(id));