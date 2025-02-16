SELECT COUNT(*)
FROM dishes
         INNER JOIN restaurants ON restaurants.id = dishes.restaurant_id
         INNER JOIN orders ON restaurants.id = orders.restaurant_id
         INNER JOIN users ON users.id = orders.user_id
WHERE dishes."name" = 'Харчо с изюминкой';