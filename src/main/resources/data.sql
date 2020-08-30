DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  company VARCHAR(250) DEFAULT NULL,
  wealth varchar(250) DEFAULT NULL
);

INSERT INTO billionaires (first_name, last_name, company,wealth) VALUES
  ('Jeff', 'Bezos','Amazon', '$64.4 B'),
  ('Bill', 'Gates', 'Microsoft', '$109.9 B'),
  ('Mark', 'Zuckerberg','Facebook', '$85.4 B');