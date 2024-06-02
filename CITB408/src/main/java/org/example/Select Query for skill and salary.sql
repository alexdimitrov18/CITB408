/* select from the employee's side */

SELECT *  FROM  employee left join  employee_skill on id = employee_id left join  skill on skills_id =  skill.id;

/* select from the skill's side */
Select  * from  skill
                    left join  employee_skill on id = skills_id
                    left join employee on  employee_skill.employee_id =  employee.id ;

/* select from the skill's side and filter by skill type */
SELECT *
FROM  skill
          LEFT JOIN  employee_skill ON  skill.id =  employee_skill.skills_id
          LEFT JOIN  employee ON  employee_skill.employee_id =  employee.id
WHERE  skill.type = 'Bus';

/* employee ID , salary , type */
SELECT  employee_skill.employee_id,  employee.salary,  skill.type
FROM  skill
          LEFT JOIN  employee_skill ON  skill.id =  employee_skill.skills_id
          LEFT JOIN  employee ON  employee_skill.employee_id =  employee.id
WHERE  skill.type = 'Bus';

/* Name, family name, salary , type */
SELECT  employee.name,  employee.family_name,  employee.salary,  skill.type
FROM  skill
          LEFT JOIN  employee_skill ON  skill.id =  employee_skill.skills_id
          LEFT JOIN  employee ON  employee_skill.employee_id =  employee.id
WHERE  skill.type = 'Bus';




SELECT  employee.name,  employee.family_name,  employee.salary,  skill.type
FROM  skill
          LEFT JOIN  employee_skill ON  skill.id =  employee_skill.skills_id
          LEFT JOIN  employee ON  employee_skill.employee_id =  employee.id
WHERE  skill.type = 'Bus'
ORDER BY  employee.salary DESC;