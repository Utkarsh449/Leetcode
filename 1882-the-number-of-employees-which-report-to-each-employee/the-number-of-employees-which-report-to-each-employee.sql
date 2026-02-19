# Write your MySQL query statement below
select e2.reports_to as employee_id, e1.name,count(e2.reports_to) as reports_count,round(AVG(e2.age)) as average_age from employees e1
join employees e2 on e1.employee_id=e2.reports_to group by e1.employee_id having count(e1.employee_id)>=1 order by e1.employee_id;