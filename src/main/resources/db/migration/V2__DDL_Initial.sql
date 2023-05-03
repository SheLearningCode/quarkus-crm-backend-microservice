CREATE SEQUENCE hibernate_sequence START 1;
CREATE TABLE IF NOT EXISTS public.student_entity
(
    role        VARCHAR(10) NOT NULL,
    password    VARCHAR(50) NOT NULL,
    nickname    VARCHAR(10) UNIQUE NOT NULL,

    student_id  UUID PRIMARY KEY,
    firstname   VARCHAR(20) NOT NULL,
    lastname    VARCHAR(20) NOT NULL,
    email       VARCHAR(255) UNIQUE NOT NULL

);
CREATE TABLE IF NOT EXISTS public.course_entity
(
    course_id   UUID PRIMARY KEY,
    title       VARCHAR(255) UNIQUE NOT NULL,
    category    VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    max_students INT NOT NULL,
    start_date  DATE
);
