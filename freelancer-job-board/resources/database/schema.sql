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

--CREATE TABLE "user" (
--    user_id SERIAL PRIMARY KEY,
--    name VARCHAR(100) UNIQUE NOT NULL,
--    email VARCHAR(100) UNIQUE NOT NULL,
--    role VARCHAR(20) NOT NULL CHECK (role IN ('client', 'freelancer')),
--    password VARCHAR(100) NOT NULL
--);

CREATE TABLE user (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
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
    job_id INTEGER NOT NULL,
    freelancer_id INTEGER REFERENCES "user"(user_id)
    cover_letter TEXT,
    status VARCHAR(30) NOT NULL DEFAULT 'submitted'
);


CREATE TABLE user_skill (
    user_id INTEGER REFERENCES "user"(user_id),
    skill_id INTEGER REFERENCES skill(skill_id),
    PRIMARY KEY (user_id, skill_id)
);
