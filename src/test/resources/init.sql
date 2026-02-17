CREATE TABLE IF NOT EXISTS teams (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO teams (name) VALUES ('Test Team A');
INSERT INTO teams (name) VALUES ('Test Team B');
INSERT INTO teams (name) VALUES ('Test Team C');
