CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    email VARCHAR(128) NOT NULL,
    password VARCHAR(64) NOT NULL,
    roles VARCHAR(64)
);
CREATE TABLE IF NOT EXISTS vacancies
(
    id BIGSERIAL PRIMARY KEY,
    company_name VARCHAR(128) NOT NULL,
    post VARCHAR(128) NOT NULL,
    expected_salary double precision NOT NULL,
    link VARCHAR(256) NOT NULL,
    contact VARCHAR(256) NOT NULL,
    status VARCHAR(128) NOT NULL,
    last_status_change date,
    user_id bigint,
    CONSTRAINT FK_UserVacancy FOREIGN KEY (user_id)
    REFERENCES users(id)
    );