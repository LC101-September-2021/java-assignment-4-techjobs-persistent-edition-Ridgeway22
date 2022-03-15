-- Part 1: Test it with SQL
id (int), name varchar(250), employer_id (int), employer varchar(250), skill varchar(250);
-- Part 2: Test it with SQL
SELECT name
FROM employer
WHERE location = "St. Louis City";
-- Part 3: Test it with SQL
DROP TABLE job;
-- Part 4: Test it with SQL
SELECT *
FROM skill
LEFT JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_Id IS NOT NULL
ORDER BY name ASC;