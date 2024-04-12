CREATE TABLE phone_number (
    id INT AUTO_INCREMENT,
    medical_record_number BIGINT(20),
    type VARCHAR(20),
    number VARCHAR(20),
    PRIMARY KEY (id),
    FOREIGN KEY (medical_record_number) REFERENCES patient(medical_record_number)
);