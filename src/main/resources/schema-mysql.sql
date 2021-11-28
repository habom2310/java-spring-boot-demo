CREATE TABLE IF NOT EXISTS employee (
    id INT(10) NOT NULL AUTO_INCREMENT,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    annualSalary DECIMAL(10,2) NOT NULL,
    hireDate DATE NOT NULL,
    superRate DECIMAL(10,2) NOT NULL,
    taxRate DECIMAL(10,2) NOT NULL,
    primary key (id)
);