SELECT CART_ID
FROM CART_PRODUCTS
WHERE NAME = 'Milk'

INTERSECT

SELECT CART_ID
FROM CART_PRODUCTS
WHERE NAME = 'Yogurt'

ORDER BY CART_ID ASC