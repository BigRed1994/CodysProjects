DROP TABLE IF EXISTS application;
DROP TABLE IF EXISTS user_skill;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS skill;

CREATE TABLE category (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE skill (
    skill_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS "user" (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(200) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE job (
    job_id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    posted_by INTEGER REFERENCES "user"(user_id),
    category_id INTEGER REFERENCES category(category_id)
);

CREATE TABLE application (
    application_id SERIAL PRIMARY KEY,
    job_id INTEGER REFERENCES job(job_id),
    freelancer_id INTEGER REFERENCES "user"(user_id),
    cover_letter TEXT,
    status VARCHAR(30) NOT NULL DEFAULT 'submitted'
);

CREATE TABLE user_skill (
    user_id INTEGER REFERENCES "user"(user_id),
    skill_id INTEGER REFERENCES skill(skill_id),
    PRIMARY KEY (user_id, skill_id)
);
-----------------------------------------------

INSERT INTO "user" (user_id, username, password_hash, role)
VALUES (1, 'cali', 'hashedpassword', 'client');


INSERT INTO category (category_id, name)
VALUES (1, 'Web Development');


INSERT INTO job (title, description, posted_by, category_id)
VALUES
('Build a Portfolio Website', 'Need a portfolio site built in React.', 1, 1),
('Fix WordPress Site', 'My site is broken after plugin updates.', 1, 1);





