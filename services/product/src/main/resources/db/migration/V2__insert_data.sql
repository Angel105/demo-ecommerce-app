-- Insert categories and capture the generated IDs
INSERT INTO category (id, name, description)
VALUES (nextval('category_seq'), 'Keyboards', 'Various types of computer keyboards'),
       (nextval('category_seq'), 'Mice', 'Computer mice with different features'),
       (nextval('category_seq'), 'Monitors', 'Computer monitors of various sizes'),
       (nextval('category_seq'), 'Printers', 'Printers for all your printing needs'),
       (nextval('category_seq'), 'Storage Devices', 'External and internal storage devices');

-- Assuming the sequences start from 1 and are incremented by 50:
-- Keyboards category will have ID 1; Mice: 51; Monitors: 101; Printers: 151; Storage Devices: 201;

-- Insert products for category 'Keyboards'
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Mechanical Keyboard', 'A high-quality mechanical keyboard', 50, 150.00, 1),
       (nextval('product_seq'), 'Wireless Keyboard', 'A sleek wireless keyboard', 30, 70.00, 1),
       (nextval('product_seq'), 'Ergonomic Keyboard', 'An ergonomic keyboard for comfortable typing', 20, 90.00, 1),
       (nextval('product_seq'), 'Gaming Keyboard', 'A keyboard with customizable RGB lighting', 40, 120.00, 1),
       (nextval('product_seq'), 'Compact Keyboard', 'A compact keyboard with a small footprint', 25, 50.00, 1),
       (nextval('product_seq'), 'Backlit Keyboard', 'A keyboard with backlit keys', 35, 80.00, 1),
       (nextval('product_seq'), 'Foldable Keyboard', 'A portable and foldable keyboard', 45, 60.00, 1),
       (nextval('product_seq'), 'Bluetooth Keyboard', 'A keyboard with Bluetooth connectivity', 50, 85.00, 1),
       (nextval('product_seq'), 'Slim Keyboard', 'A slim and stylish keyboard', 40, 55.00, 1),
       (nextval('product_seq'), 'Wired Keyboard', 'A traditional wired keyboard', 60, 25.00, 1);

-- Insert products for category 'Mice'
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Wireless Mouse', 'A high-precision wireless mouse', 50, 35.00, 51),
       (nextval('product_seq'), 'Gaming Mouse', 'A mouse with customizable buttons', 30, 60.00, 51),
       (nextval('product_seq'), 'Ergonomic Mouse', 'An ergonomic mouse for comfortable use', 20, 40.00, 51),
       (nextval('product_seq'), 'Bluetooth Mouse', 'A mouse with Bluetooth connectivity', 40, 25.00, 51),
       (nextval('product_seq'), 'Vertical Mouse', 'A vertically designed mouse for reduced wrist strain', 25, 50.00,
        51),
       (nextval('product_seq'), 'Trackball Mouse', 'A mouse with a trackball for precise control', 35, 45.00, 51),
       (nextval('product_seq'), 'Laser Mouse', 'A high-precision laser mouse', 45, 55.00, 51),
       (nextval('product_seq'), 'Compact Mouse', 'A small and portable mouse', 50, 20.00, 51),
       (nextval('product_seq'), 'Silent Mouse', 'A mouse with silent clicks', 40, 30.00, 51),
       (nextval('product_seq'), 'Wired Mouse', 'A traditional wired mouse', 60, 10.00, 51);

-- Insert products for category 'Monitors'
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (nextval('product_seq'), '24-inch Monitor', 'A 24-inch full HD monitor', 15, 150.00, 101),
       (nextval('product_seq'), '27-inch Monitor', 'A 27-inch high-resolution monitor', 20, 250.00, 101),
       (nextval('product_seq'), 'Curved Monitor', 'A 32-inch curved monitor', 10, 350.00, 101),
       (nextval('product_seq'), 'Gaming Monitor', 'A monitor with high refresh rate', 25, 300.00, 101),
       (nextval('product_seq'), '4K Monitor', 'A 4K ultra HD monitor', 8, 400.00, 101),
       (nextval('product_seq'), 'Ultrawide Monitor', 'A 34-inch ultrawide monitor', 5, 450.00, 101),
       (nextval('product_seq'), 'Portable Monitor', 'A lightweight portable monitor', 18, 200.00, 101),
       (nextval('product_seq'), 'Touchscreen Monitor', 'A monitor with touchscreen capability', 12, 275.00, 101),
       (nextval('product_seq'), 'Slim Bezel Monitor', 'A monitor with slim bezels', 14, 220.00, 101),
       (nextval('product_seq'), 'IPS Monitor', 'A monitor with IPS panel', 22, 180.00, 101);

-- Insert products for category 'Printers'
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Inkjet Printer', 'A multi-function inkjet printer', 10, 120.00, 151),
       (nextval('product_seq'), 'Laser Printer', 'A high-speed laser printer', 12, 220.00, 151),
       (nextval('product_seq'), 'All-in-One Printer', 'A printer that also scans and copies', 8, 250.00, 151),
       (nextval('product_seq'), 'Photo Printer', 'A printer designed for photos', 6, 200.00, 151),
       (nextval('product_seq'), 'Wireless Printer', 'A printer with wireless capability', 15, 180.00, 151),
       (nextval('product_seq'), 'Compact Printer', 'A portable and compact printer', 20, 100.00, 151),
       (nextval('product_seq'), 'Office Printer', 'A printer designed for office use', 7, 300.00, 151),
       (nextval('product_seq'), 'Duplex Printer', 'A printer that supports duplex printing', 9, 190.00, 151),
       (nextval('product_seq'), '3D Printer', 'A printer for 3D printing', 5, 800.00, 151),
       (nextval('product_seq'), 'Label Printer', 'A printer for printing labels', 25, 75.00, 151);

-- Insert products for category 'Storage Devices'
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'External Hard Drive', 'A 1TB external hard drive', 30, 100.00, 201),
       (nextval('product_seq'), 'USB Flash Drive', 'A 64GB USB flash drive', 50, 25.00, 201),
       (nextval('product_seq'), 'SSD', 'A 512GB solid state drive', 20, 150.00, 201),
       (nextval('product_seq'), 'MicroSD Card', 'A 128GB microSD card', 40, 40.00, 201),
       (nextval('product_seq'), 'Network Attached Storage', 'A NAS for home use', 10, 300.00, 201),
       (nextval('product_seq'), 'External SSD', 'A 1TB external solid state drive', 15, 200.00, 201),
       (nextval('product_seq'), 'Portable SSD', 'A small and portable SSD', 25, 180.00, 201),
       (nextval('product_seq'), 'Thumb Drive', 'A compact USB thumb drive', 60, 15.00, 201),
       (nextval('product_seq'), 'Backup Drive', 'A 2TB backup drive', 18, 120.00, 201),
       (nextval('product_seq'), 'Memory Card Reader', 'A universal memory card reader', 50, 20.00, 201);