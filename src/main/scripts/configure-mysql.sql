#Create Databases
CREATE DATABASE recipe_dev;
CREATE DATABASE recipe_prod;

#Create database service accounts
CREATE USER 'recipe_dev_user'@'localhost' IDENTIFIED BY 'fafik';
CREATE USER 'recipe_prod_user'@'localhost' IDENTIFIED BY 'fafik';
CREATE USER 'recipe_dev_user'@'%' IDENTIFIED BY 'fafik';
CREATE USER 'recipe_prod_user'@'%' IDENTIFIED BY 'fafik';

#Database grants
GRANT SELECT ON  recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT INSERT ON  recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT DELETE ON  recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT UPDATE ON recipe_dev.* to 'recipe_dev_user'@'localhost';

GRANT SELECT ON  recipe_prod.* to 'recipe_prod_user'@'localhost';
GRANT INSERT ON  recipe_prod.* to 'recipe_prod_user'@'localhost';
GRANT DELETE ON  recipe_prod.* to 'recipe_prod_user'@'localhost';
GRANT UPDATE ON  recipe_prod.* to 'recipe_prod_user'@'localhost';

GRANT SELECT ON  recipe_dev.* to 'recipe_dev_user'@'%';
GRANT INSERT ON  recipe_dev.* to 'recipe_dev_user'@'%';
GRANT DELETE ON  recipe_dev.* to 'recipe_dev_user'@'%';
GRANT UPDATE ON recipe_dev.* to 'recipe_dev_user'@'%';

GRANT SELECT ON  recipe_prod.* to 'recipe_prod_user'@'%';
GRANT INSERT ON  recipe_prod.* to 'recipe_prod_user'@'%';
GRANT DELETE ON  recipe_prod.* to 'recipe_prod_user'@'%';
GRANT UPDATE ON  recipe_prod.* to 'recipe_prod_user'@'%';