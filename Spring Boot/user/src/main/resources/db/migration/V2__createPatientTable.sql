CREATE TABLE patient (
    medical_record_number BIGINT(20) AUTO_INCREMENT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    date_of_birth DATE,
    gender VARCHAR(20),
    street_address VARCHAR(50),
    city VARCHAR(20),
    state VARCHAR(20),
    postal_code BIGINT(10),
    email VARCHAR(50),
    PRIMARY KEY(medical_record_number)
);
