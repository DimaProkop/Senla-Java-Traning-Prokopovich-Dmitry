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
/* task 10 */
SELECT model, price FROM printer WHERE price = (SELECT MAX(price) FROM printer);
/* task 11 */
SELECT AVG(speed) FROM pc;
/* task 12 */
SELECT AVG(speed) FROM laptop WHERE price > 1000;
/* task 13 */
SELECT MAX(speed) FROM product, pc WHERE product.model = pc.model AND product.maker= 'HP' UNION
SELECT MAX(speed) FROM product, pc WHERE product.model = pc.model AND product.maker= 'Asus' UNION
SELECT MAX(speed) FROM product, pc WHERE product.model = pc.model AND product.maker= 'MSI Gaming';
/* task 14 */
SELECT speed, AVG(price) FROM pc GROUP BY speed;
/* task 15 */
SELECT hd FROM pc GROUP BY hd HAVING COUNT(hd) > 2;
/* task 16 */
SELECT MAX(model), MIN(model), speed, ram FROM pc GROUP BY speed, ram HAVING COUNT(speed)>=2 AND COUNT(ram)>=2;
/* task 17 */
SELECT DISTINCT product.type, laptop.model, laptop.speed FROM product, laptop WHERE product.type = 'Laptop' AND laptop.speed < (SELECT MIN(speed) FROM pc);
/* task 18 */
SELECT maker, MIN(price) FROM product, printer WHERE product.model = printer.model AND printer.color = 'y' GROUP BY maker;
/* task 19 */
SELECT maker, AVG(screen) FROM product, laptop WHERE product.model = laptop.model GROUP BY maker;
/* task 20 */
SELECT maker, COUNT(model) FROM product WHERE product.type = 'PC' GROUP BY maker HAVING COUNT(model) >= 3;
/* task 21 */
SELECT maker, MAX(price) FROM product, pc WHERE product.model = pc.model GROUP BY maker;
/* task 22 */
SELECT speed, AVG(price) FROM pc GROUP BY speed HAVING speed > 600;
/* task 23 */
SELECT DISTINCT maker FROM product WHERE type = 'Laptop' AND maker IN (SELECT maker FROM product WHERE type = 'PC' AND maker IN(SELECT maker FROM pc, laptop WHERE pc.speed >=750 and laptop.speed >=750));
/* task 24 */
SELECT DISTINCT model FROM laptop WHERE laptop.price = (SELECT MAX(price) FROM laptop) UNION
SELECT model FROM pc WHERE pc.price = (SELECT MAX(price) FROM pc) UNION
SELECT model FROM printer WHERE printer.price = (SELECT MAX(price) FROM printer);
/* task 25 */
SELECT DISTINCT maker FROM product WHERE type = 'Printer' AND maker IN (SELECT maker FROM product
  JOIN pc on product.model = pc.model WHERE type = 'PC' and ram = (SELECT MIN(ram) FROM pc) AND pc.speed = (SELECT MAX(speed) FROM pc));