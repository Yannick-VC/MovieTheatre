CREATE TABLE IF NOT EXISTS movies (
  movieid int(5) NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  duration varchar(45) DEFAULT NULL,
  rating varchar (15) DEFAULT NULL,
  PRIMARY KEY(movieid)
);

CREATE TABLE IF NOT EXISTS users (
  id int(5) NOT NULL AUTO_INCREMENT,
  username varchar(45) DEFAULT NULL,
  passwordhash varchar(100) DEFAULT NULL,
  role varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  PRIMARY KEY(id)
);