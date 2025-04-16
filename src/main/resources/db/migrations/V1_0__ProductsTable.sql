CREATE TYPE product_category AS ENUM (
  'Electronics',
  'Home & Kitchen',
  'Clothing',
  'Accessories',
  'Sports',
  'Musical Instrument',
  'Footwear',
  'Home & Appliances',
  'Stationery',
  'Toys & Games'
);
CREATE TABLE products
(
    sku         VARCHAR(30)      NOT NULL,
    price       DOUBLE PRECISION NOT NULL CHECK (price > 0),
    description VARCHAR(200)     NOT NULL,
    category    VARCHAR(30)      NOT NULL,
    PRIMARY KEY (sku)
);
