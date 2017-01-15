/* task 1 */
SELECT model, speed, hd FROM pc WHERE price < 500;
/* task 2 */
SELECT maker FROM product, printer WHERE product.model = printer.model;
/* task 3 */
SELECT model, ram, screen FROM laptop WHERE price > 1000;
/* task 4 */
SELECT * FROM printer WHERE color = 'y';
/* task 5 */
SELECT model, speed, hd FROM pc WHERE cd IN (12, 24) AND price < 600;
/* task 6 */
SELECT maker, speed FROM product, laptop WHERE product.model = laptop.model AND laptop.hd >= 10;
/* task 7 */
SELECT product.model, price FROM product, laptop WHERE product.model = laptop.model AND product.maker= 'Lenovo' UNION
SELECT product.model, price FROM product, laptop WHERE product.model = laptop.model AND product.maker= 'HP' UNION
SELECT product.model, price FROM product, laptop WHERE product.model = laptop.model AND product.maker= 'Acer';
/* task 8 */
SELECT maker FROM product WHERE type = 'PC';
/* task 9 */
SELECT maker FROM product, pc WHERE pc.speed >= 450;
/* task 10 ???? */
SELECT model, max(price) FROM printer;
/* task 11 */
SELECT AVG(speed) FROM laptop WHERE price > 1000;