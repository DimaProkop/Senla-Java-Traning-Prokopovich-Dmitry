START TRANSACTION;
USE hotel;

INSERT service(name, price, section, startDate, finalDate) VALUE
  ('Service1', 55.5, 'mandatory', '2003-01-08', '2016-6-6'),
  ('Service2', 33.4, 'food', '2014-5-5', '2015-5-5'),
  ('Service3', 240, 'place', '2013-5-8', '2014-7-15');

INSERT guest(name) VALUE
  ('User1'),
  ('User2'),
  ('User3');

INSERT room(price, capacity, status, section, rating) VALUE
  (150, 4, 'free', 'single', 9),
  (243, 3, 'free', 'standart', 7),
  (200, 5, 'free', 'luks', 10);

COMMIT;