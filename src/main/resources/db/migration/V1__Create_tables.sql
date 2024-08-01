CREATE TABLE district (
    code INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE district_part (
    code INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    district_code INT NOT NULL,
    FOREIGN KEY (district_code) REFERENCES district(code)
);
