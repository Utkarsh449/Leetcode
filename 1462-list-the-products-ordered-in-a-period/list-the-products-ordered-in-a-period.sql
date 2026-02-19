# Write your MySQL query statement below
-- select
--     p.product_name,
--     sum(o.unit) as unit
-- from Products p
-- join Orders o on p.product_id = o.product_id
-- where o.order_date between '2020-02-01' and '2020-02-29'
-- group by p.product_id, p.product_name
-- having sum(o.unit) >= 100
-- order by p.product_name;


SELECT 
    p.product_name,
    total.unit
FROM Products p
JOIN (
    SELECT 
        product_id,
        SUM(unit) AS unit
    FROM Orders
    WHERE order_date >= '2020-02-01' 
      AND order_date <= '2020-02-29'
    GROUP BY product_id
    HAVING SUM(unit) >= 100
) total ON p.product_id = total.product_id
ORDER BY p.product_name;