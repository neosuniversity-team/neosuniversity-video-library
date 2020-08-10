
/*
  Ejecutar el create database unicamente si no ha creado la bd con mysql workbench
*/
CREATE DATABASE videodb CHARACTER SET utf8 COLLATE utf8_spanish_ci;

/*
  Crear el usuario neosvideo, unicamente si no ha creado el usuario en mysql workbench

*/

create user 'neosvideo'@'localhost' identified by 'neosvideo';


GRANT ALL PRIVILEGES ON videodb.* TO 'neosvideo'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;
