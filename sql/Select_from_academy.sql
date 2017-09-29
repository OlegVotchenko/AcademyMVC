# Select subject's name with rename of columns
SELECT subjects.name 'Subject of study' 
	FROM subjects;

# Select All students and teachers
SELECT * 
	FROM passports;

# Select first 10 students and teachers
SELECT * 
	FROM passports LIMIT 10;

# Sort students and teachers for Alphabetizing (last name)
# and select 10 members skipping the first 2 members
SELECT * 
	FROM passports ORDER BY last_name ASC LIMIT 10 OFFSET 2;

# Select variety of marks (with sorting) in examinations
SELECT DISTINCT mark 
	FROM marks ORDER BY mark ASC;

# Select all type of electronics and programming in the subjects
SELECT * 
	FROM subjects
	WHERE name LIKE '%electronics%' OR name LIKE '%programming%';

# Select subject's name for group's name is "rk-17-1" (does not work correct)
SELECT groups.name 'Group', subjects.name 'Subject'  
	FROM groups, subjects 
	WHERE groups.name = 'rk-17-1';	
	

# Select all students, who has mark "5" by Physical Culture (does not work correct)
#SELECT DISTINCT groups.name, passports.first_name, passports.last_name, subjects.name, marks.mark 
#	FROM groups, passports, subjects, marks
#	WHERE subjects.name = 'Physical Culture' AND marks.mark = '5'
#	ORDER BY groups.name;



