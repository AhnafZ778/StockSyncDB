SELECT * from products where ID = 1;

Select id, sku, name, quantity From products where active = 1;

Select * from Products where price Between 10 and 50;

SELECT sku, name, quantity from Products Order by quantity DESC;

Select count(*) as total_products From Products where active = 1;

Select SUM(Quantity*Price) As total_value from Products where active = 1;

Select Avg(price) As avg_price from Products where active = 1;

Select sku, name, price From products where active = 1 Order by price DESC Limit 1;

Select * from products where name like "%Widget%";

Update products set Active = 0 where sku = "OP-013";

Select * from products where Active = 1;

Select * from products;

Update products set Active = 1 where sku = "OP-013"

Select * from products where Active = 1;

