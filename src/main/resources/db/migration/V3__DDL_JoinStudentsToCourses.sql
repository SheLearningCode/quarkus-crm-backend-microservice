CREATE TABLE IF NOT EXISTS public.students_courses
(
    student_id UUID NOT NULL,
    course_id UUID NOT NULL,
    PRIMARY KEY(student_id, course_id)
);

ALTER TABLE public.students_courses
    ADD CONSTRAINT
        fk_students_courses__student_entity FOREIGN KEY (student_id) REFERENCES public."student_entity"(student_id);

ALTER TABLE public.students_courses
    ADD CONSTRAINT
        fk_students_courses__course_entity FOREIGN KEY (course_id) REFERENCES public."course_entity"(course_id) ;