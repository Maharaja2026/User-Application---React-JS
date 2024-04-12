CREATE TABLE user (
  user_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(50) NOT NULL,
  user_contact BIGINT(20) NOT NULL,
  user_email VARCHAR(50) NOT NULL,
  user_password VARCHAR(50) NOT NULL,
  PRIMARY KEY(user_id)
);