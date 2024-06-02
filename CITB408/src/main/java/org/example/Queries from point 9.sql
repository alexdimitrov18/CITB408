/* Total payloads */
SELECT Count(*) as "total payloads" FROM java_single_schema.payload;

/* Total profil from payloads */
SELECT  Count(r.id) as "total orders", SUM(p.price) as "total profit" 
FROM receipt r join purchase p on r.purchases_id = p.id;

/* Total trips made by each employee */
Select e.name, e.family_name, c.name, Count(p.id) as "total trips made "
from employee e left join company c on e.company_id = c.id 
left join purchase p on c.id=p.company_id 
group by e.name ;

/* Company's profit for a period ot time */
Select c.name as "Company name" , sum(p.price) as "total profit"  from company c 
 left join purchase p on c.id = p.company_id 
 left join receipt r on p.id = r.purchases_id
 where end_time between '2021-01-09 21:46:09.000000' and '2026-01-09 21:46:09.000000'
 group by c.name ;
 
 /* Each employee's profit */
 Select e.name, e.family_name,Sum(p.price ) as "total profit"
 from purchase p 
 left join employee e on p.employee_id = e.id 
 group by e.name;
 
 