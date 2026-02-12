# Write your MySQL query statement below
SELECT
a.product_id
,round(coalesce((SUM(a.price*b.units)/sum(b.units)),0),2) AS average_price
FROM Prices a
LEFT JOIN UnitsSold b
ON a.product_id = b.product_id
AND b.purchase_date >= a.start_date AND b.purchase_date <= a.end_date
GROUP BY 1