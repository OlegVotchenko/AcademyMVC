#Comment
DROP DATABASE IF EXISTS academy;
CREATE DATABASE academy;
USE academy;

create TABLE passports
(
	id SERIAL,
	first_name VARCHAR(64),
	last_name VARCHAR(64),
	PRIMARY KEY(id)
);

create TABLE students
(
	id SERIAL,
	id_passport BIGINT UNSIGNED UNIQUE,
	date DATE
		COMMENT 'Birthday of student',
	PRIMARY KEY(id),
	FOREIGN KEY (id_passport) REFERENCES passports(id)
		ON DELETE RESTRICT ON UPDATE CASCADE
);

create TABLE forms
(
	id SERIAL,
	name VARCHAR(64)
		COMMENT 'Form of study',
	PRIMARY KEY(id)
);

create TABLE groups
(
	id SERIAL,
	name VARCHAR(8),
	id_form BIGINT UNSIGNED,
	PRIMARY KEY(id),
	FOREIGN KEY (id_form) REFERENCES forms(id)
		ON DELETE RESTRICT ON UPDATE CASCADE
);

create TABLE students_groups
(
	id_student BIGINT UNSIGNED UNIQUE,
	id_group BIGINT UNSIGNED,
    FOREIGN KEY (id_student) REFERENCES students(id)
		ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_group) REFERENCES groups(id)
		ON DELETE RESTRICT ON UPDATE CASCADE
);

create TABLE teachers
(
	id SERIAL,
	id_passport BIGINT UNSIGNED UNIQUE,
	PRIMARY KEY(id),
	FOREIGN KEY (id_passport) REFERENCES passports(id)
		ON DELETE RESTRICT ON UPDATE CASCADE
);

create TABLE subjects
(
	id SERIAL,
	name VARCHAR(64),
	PRIMARY KEY(id)
);

create TABLE subjects_teachers
(
	id_subject BIGINT UNSIGNED,
	id_teacher BIGINT UNSIGNED,
    FOREIGN KEY (id_subject) REFERENCES subjects(id)
		ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_teacher) REFERENCES teachers(id)
		ON DELETE RESTRICT ON UPDATE CASCADE
);

create TABLE marks
(
	id SERIAL,
	id_student BIGINT UNSIGNED,
	id_group BIGINT UNSIGNED,
	id_subject BIGINT UNSIGNED,
	mark ENUM("1", "2", "3", "4", "5", "0") NOT NULL DEFAULT "0",
	date DATE
		COMMENT 'Date of examination',
	id_teacher BIGINT UNSIGNED,
	PRIMARY KEY(id),
	FOREIGN KEY (id_student) REFERENCES students(id)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (id_group) REFERENCES groups(id)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (id_subject) REFERENCES subjects(id)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (id_teacher) REFERENCES teachers(id)
		ON DELETE RESTRICT ON UPDATE CASCADE
);


