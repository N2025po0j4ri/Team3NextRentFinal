CREATE DATABASE rentapplications;

CREATE USER 'customer'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON rentapplications.* TO 'customer'@'localhost';

FLUSH PRIVILEGES;

USE rentapplications;

CREATE TABLE appinfo (
id INT AUTO_INCREMENT PRIMARY KEY,
Name VARCHAR(100) NOT NULL,
Description TEXT,
Organization VARCHAR(100),
Platforms TEXT, 
Links TEXT,
Price DECIMAL(10, 2));

INSERT INTO appinfo (Name, Description, Organization, Platforms, Links, Price)
VALUES ('Zillow App', 'A rental app', 'Zillow', 'Apple/Android', 'https://apps.apple.com/us/app/zillow-rentals/id538946076', 0);
