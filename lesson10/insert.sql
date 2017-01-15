INSERT printer(code, model, color, type, price) VALUE
  (1255, 'D2000', 'y', 'Lazer', 210),
  (952, 'F3000', 'n', 'Matrix', 652),
  (322, 'J1200', 'y', 'Jet', 150);

INSERT pc(code, model, speed, ram, hd, cd, price) VALUE
  (136, '25GE', 3000, 4096, 500, '4x', 365),
  (2750, '22-b0', 3500, 6144, 750, '8x', 450),
  (3192, 'Vivo Aio 2000', 4000, 8192, 1000, '16x', 685);

INSERT laptop(code, model, speed, ram, hd, price, screen) VALUE
  (1845, 'Acer v3-571g', 2500, 8192, 240, 720, 15),
  (214, 'HP4545', 2100, 6144, 500, 630, 15),
  (550, 'Lenovo ideaPad', 3000, 16384, 250, 950, 17);

INSERT product(maker, model, type) VALUE
  ('Lenovo', 'Lenovo ideaPad', 'Laptop'),
  ('Xerox', 'd2000', 'Printer'),
  ('Acer', 'Acer v3-571g', 'Laptop'),
  ('HP', 'HP4545', 'Laptop'),
  ('HP', 'J1200', 'Printer'),
  ('Canon', 'F3000', 'Printer'),
  ('Asus', 'Vivo Aio 2000', 'PC'),
  ('HP', '22-b0', 'PC'),
  ('MSI Gaming', '25GE', 'PC');
