# Write your MySQL query statement below
select e.name AS name, b.bonus as bonus
from Employee e
LEFT JOIN Bonus b
on e.empId = b.empId
where b.bonus < 1000 OR  b.bonus IS NULL