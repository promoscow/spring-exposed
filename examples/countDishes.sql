SELECT restaurants.id, restaurants."name"
FROM restaurants
         RIGHT JOIN dishes ON restaurants.id = dishes.restaurant_id
         RIGHT JOIN orders ON dishes.id = orders.dish_id
WHERE orders.user_id = '1031f963-957c-425f-962e-767080a699cb';