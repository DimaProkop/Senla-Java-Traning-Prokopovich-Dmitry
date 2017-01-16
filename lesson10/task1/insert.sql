INSERT product(maker, model, type) VALUE
  ('Lenovo', 'Lenovo ideaPad', 'Laptop'),
  ('Xerox', 'd2000', 'Printer'),
  ('Acer', 'Acer v3-571g', 'Laptop'),
  ('HP', 'HP4545', 'Laptop'),
  ('HP', 'J1200', 'Printer'),
  ('Canon', 'F3000', 'Printer'),
  ('Asus', 'Vivo Aio 2000', 'PC'),
  ('HP', '22-b0', 'PC'),
  ('MSI Gaming', '25GE', 'PC'),
  ('Lenovo', 'Lenovo Z51-70', 'Laptop'),
  ('Lenovo', 'Lenovo Y700', 'Laptop'),
  ('Acer', 'Acer Aspire ES1', 'Laptop'),
  ('HP', 'HP 255', 'Laptop'),
  ('HP', 'HP 15-ac159ur', 'Laptop'),
  ('Asus', 'Zen AiO Pro', 'PC'),
  ('Asus', 'A4110', 'PC'),
  ('HP', 'Zen AiO Pro', 'PC'),
  ('HP', 'ENVY 24-n271ur', 'PC'),
  ('MSI Gaming', 'GT83VR', 'PC'),
  ('MSI Gaming', 'MSI GL62', 'PC');

INSERT printer(code, model, color, type, price) VALUE
  (1255, 'D2000', 'y', 'Lazer', 210),
  (952, 'F3000', 'n', 'Matrix', 652),
  (322, 'J1200', 'y', 'Jet', 150);

INSERT pc(code, model, speed, ram, hd, cd, price) VALUE
  (136, '25GE', 3000, 4096, 500, '4x', 365),
  (2750, '22-b0', 3500, 6144, 750, '12x', 450),
  (3192, 'Vivo Aio 2000', 4000, 8192, 1000, '16x', 685),
  (1548, 'Zen AiO', 5000, 3072, 500, '8x', 430),
  (2751, 'ENVY 24-n271ur', 3500, 6144, 750, '12x', 450),
  (3193, 'GT83VR', 3500, 8192, 1000, '16x', 785),
  (3195, 'MSI GL62', 4500, 8192, 1500, '24x', 995),
  (1363, 'A4110', 3500, 8192, 750, '8x', 525),
  (265,	'Zen AiO Pro', 3100, 4096, 500, '8x', 430);


INSERT laptop(code, model, speed, ram, hd, price, screen) VALUE
  (1845, 'Acer v3-571g', 2500, 8192, 240, 720, 15),
  (214, 'HP4545', 2100, 6144, 500, 630, 15),
  (550, 'Lenovo ideaPad', 3000, 16384, 250, 1020, 17),
  (1843, 'Lenovo Z51-70', 2600, 8192, 500, 653, 17),
  (2141, 'Lenovo Y700', 2800, 8192, 500, 800, 15),
  (5501, 'HP 255', 2100, 4048, 500, 550, 15),
  (1844, 'HP 15-ac159ur', 2500, 8192, 1000, 860, 15),
  (2142, 'Acer Aspire ES1', 2600, 8192, 750, 810, 15);
