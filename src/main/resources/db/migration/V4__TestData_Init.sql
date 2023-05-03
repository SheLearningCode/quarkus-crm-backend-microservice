-- Insert test data for COURSE
INSERT INTO public."course_entity"(course_id, title, category, description, max_students, start_date)
DEFAULT VALUES;

-- Insert test data for STUDENT
INSERT INTO public."student_entity"(role, password, nickname, student_id, firstname, lastname, email)
VALUES ('Admin', 'password123', 'more123', gen_random_uuid(), 'max', 'mustermann', 'max.muster@mann.de');
