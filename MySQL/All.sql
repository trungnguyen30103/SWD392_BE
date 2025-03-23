USE blindbox_db;

INSERT INTO role (role_id, name) VALUES
(1, 'Admin'),
(2, 'User');

INSERT INTO category (category_id, name, description) VALUES
(1, 'Toys', 'Toys for kids'),
(2, 'Electronics', 'Electronic gadgets'),
(3, 'Stationery', 'School and office supplies'),
(4, 'Collectibles', 'Limited edition and rare items');


ALTER TABLE user
DROP CONSTRAINT user_chk_1,
ADD CONSTRAINT user_chk_1 CHECK (phone LIKE '091%' AND LENGTH(phone) = 10);

INSERT INTO user (user_id, username, email, password, phone, address, role_id, fullname) VALUES
(1, 'admin', 'admin@gmail.com', 'admin123', '0911351638', '123 Ho Chi Minh.', 1, 'Admin User'),
(2, 'customer1', 'customer1@gmail.com', '12345', '0911351639', '456 Ho Chi Minh.', 2, 'Customer User'),
(3, 'customer2', 'customer2@gmail.com', '12345', '0911351637', '789 Ho Chi Minh.', 2, 'Customer Userr');

INSERT INTO blindbox (blindbox_id, name, description, price, stock_quantity, category_id, rating, stock) VALUES
(1, 'Mystery Toy Blindbox', 'A surprise toy from various series. Collect them all!', 15.99, 150, 1, 4.3, 150),
(2, 'Mystery Collectible Blindbox', 'A box containing random collectible figures or memorabilia.', 22.50, 100, 4, 4.7, 100),
(3, 'Surprise Gadget Blindbox', 'Random gadgets like keychains, mini speakers, etc., inside each box.', 30.00, 80, 2, 4.0, 80),
(4, 'Mystery Stationery Blindbox', 'Random assortment of school and office supplies with mystery items.', 12.00, 200, 3, 4.5, 200),
(5, 'Surprise Plush Toy Blindbox', 'A random plush toy of various designs, perfect for gifting or collecting.', 18.99, 120, 1, 4.8, 120),
(6, 'Anime Figure Blindbox', 'Random anime characters as collectible figures.', 25.99, 80, 4, 4.6, 80),
(7, 'Mini DIY Kit Blindbox', 'A random DIY kit, such as crafting, building, or art kits.', 19.99, 90, 2, 4.2, 90),
(8, 'Surprise Jewelry Blindbox', 'Random surprise jewelry such as bracelets, rings, or necklaces.', 35.00, 60, 4, 4.5, 60),
(9, 'Mystery Home Decor Blindbox', 'Surprise home decor items such as vases, wall art, or figurines.', 45.00, 50, 3, 4.3, 50),
(10, 'Mystery Kids Blindbox', 'Fun and educational toys for kids inside each mystery box.', 12.99, 100, 1, 4.4, 100),
(11, 'Gadget Accessories Blindbox', 'Random accessories for gadgets like phone cases, chargers, etc.', 20.00, 75, 2, 4.1, 75),
(12, 'Limited Edition Art Blindbox', 'Rare and limited edition artwork, prints, or collectibles.', 60.00, 30, 4, 4.9, 30),
(13, 'Mystery Puzzle Blindbox', 'A random puzzle game, either jigsaw or logic puzzles.', 10.00, 150, 1, 4.0, 150),
(14, 'Surprise Music Blindbox', 'Random music-related items such as CDs, vinyls, or instruments.', 25.00, 50, 2, 4.5, 50),
(15, 'Surprise Food Blindbox', 'Random snacks or food items from different countries inside each box.', 15.00, 200, 3, 4.2, 200),
(16, 'Luxury Fashion Blindbox', 'Luxury fashion accessories like scarves, bags, or shoes inside a mystery box.', 100.00, 20, 4, 4.7, 20);



INSERT INTO product (product_id, name, description, price, stock_quantity, category_id) VALUES
(1, 'Toy A (Mystery Blindbox)', 'A random toy that is part of a mystery blind box series.', 15.00, 150, 1),
(2, 'Plush Bunny (Mystery Blindbox)', 'A plush bunny that could be part of a surprise blind box series.', 20.00, 100, 1),
(3, 'Mini Bluetooth Speaker (Mystery Blindbox)', 'A small Bluetooth speaker that may come in your mystery blind box.', 25.00, 75, 2),
(4, 'Stationery Set (Mystery Blindbox)', 'A mix of pens, notebooks, and other stationery items that come in a mystery box.', 10.00, 200, 3),
(5, 'Random Collectible Figure (Mystery Blindbox)', 'A mystery collectible figure from various fandoms or series.', 22.50, 80, 4),
(6, 'Anime Character Figure (Mystery Blindbox)', 'A random anime character figure from various series.', 25.00, 50, 4),
(7, 'Mini DIY Craft Kit (Mystery Blindbox)', 'A random DIY kit, perfect for building or crafting.', 19.99, 90, 2),
(8, 'Surprise Jewelry Set (Mystery Blindbox)', 'A set of random jewelry items, including rings, necklaces, and bracelets.', 30.00, 60, 4),
(9, 'Home Decor Item (Mystery Blindbox)', 'A random home decor item that will surprise you.', 45.00, 50, 3),
(10, 'Kids Educational Toy (Mystery Blindbox)', 'A toy that is both fun and educational for children.', 12.00, 100, 1),
(11, 'Gadget Accessory (Mystery Blindbox)', 'Random gadgets accessories such as chargers and cases.', 15.00, 75, 2),
(12, 'Limited Edition Art Print (Mystery Blindbox)', 'A random piece of limited edition art or print.', 60.00, 30, 4),
(13, 'Jigsaw Puzzle (Mystery Blindbox)', 'A surprise jigsaw puzzle from various categories.', 10.00, 150, 1),
(14, 'Music Memorabilia (Mystery Blindbox)', 'Surprise items related to music, including CDs, vinyl, or other music-related goods.', 25.00, 50, 2),
(15, 'International Snacks (Mystery Blindbox)', 'A collection of random snacks from around the world.', 15.00, 200, 3),
(16, 'Luxury Fashion Accessory (Mystery Blindbox)', 'Luxury fashion accessories inside a mystery box.', 100.00, 20, 4);


INSERT INTO cart (cart_id, user_id) VALUES
(1, 2),
(2, 3);

INSERT INTO cartitem (cart_item_id, cart_id, product_id, quantity, price) VALUES
(1, 1, 1, 2, 30.00),
(2, 1, 5, 1, 22.50),
(3, 2, 3, 2, 50.00),
(4, 2, 10, 1, 12.00);


INSERT INTO blindboximage (blindbox_image_id, blindbox_id, image_url, alt_text) VALUES
(1, 1, 'https://via.placeholder.com/300?text=Mystery+Toy+Blindbox+1', 'Mystery Toy Blindbox - Image 1'),
(2, 1, 'https://via.placeholder.com/300?text=Mystery+Toy+Blindbox+2', 'Mystery Toy Blindbox - Image 2'),
(3, 2, 'https://via.placeholder.com/300?text=Mystery+Collectible+Blindbox+1', 'Mystery Collectible Blindbox - Image 1'),
(4, 2, 'https://via.placeholder.com/300?text=Mystery+Collectible+Blindbox+2', 'Mystery Collectible Blindbox - Image 2'),
(5, 3, 'https://via.placeholder.com/300?text=Surprise+Gadget+Blindbox+1', 'Surprise Gadget Blindbox - Image 1'),
(6, 3, 'https://via.placeholder.com/300?text=Surprise+Gadget+Blindbox+2', 'Surprise Gadget Blindbox - Image 2'),
(7, 4, 'https://via.placeholder.com/300?text=Mystery+Stationery+Blindbox+1', 'Mystery Stationery Blindbox - Image 1'),
(8, 4, 'https://via.placeholder.com/300?text=Mystery+Stationery+Blindbox+2', 'Mystery Stationery Blindbox - Image 2'),
(9, 5, 'https://via.placeholder.com/300?text=Surprise+Plush+Toy+Blindbox+1', 'Surprise Plush Toy Blindbox - Image 1'),
(10, 5, 'https://via.placeholder.com/300?text=Surprise+Plush+Toy+Blindbox+2', 'Surprise Plush Toy Blindbox - Image 2'),
(11, 6, 'https://via.placeholder.com/300?text=Anime+Figure+Blindbox+1', 'Anime Figure Blindbox - Image 1'),
(12, 6, 'https://via.placeholder.com/300?text=Anime+Figure+Blindbox+2', 'Anime Figure Blindbox - Image 2'),
(13, 7, 'https://via.placeholder.com/300?text=Mini+DIY+Kit+Blindbox+1', 'Mini DIY Kit Blindbox - Image 1'),
(14, 7, 'https://via.placeholder.com/300?text=Mini+DIY+Kit+Blindbox+2', 'Mini DIY Kit Blindbox - Image 2'),
(15, 8, 'https://via.placeholder.com/300?text=Surprise+Jewelry+Blindbox+1', 'Surprise Jewelry Blindbox - Image 1'),
(16, 8, 'https://via.placeholder.com/300?text=Surprise+Jewelry+Blindbox+2', 'Surprise Jewelry Blindbox - Image 2'),
(17, 9, 'https://via.placeholder.com/300?text=Mystery+Home+Decor+Blindbox+1', 'Mystery Home Decor Blindbox - Image 1'),
(18, 9, 'https://via.placeholder.com/300?text=Mystery+Home+Decor+Blindbox+2', 'Mystery Home Decor Blindbox - Image 2'),
(19, 10, 'https://via.placeholder.com/300?text=Mystery+Kids+Blindbox+1', 'Mystery Kids Blindbox - Image 1'),
(20, 10, 'https://via.placeholder.com/300?text=Mystery+Kids+Blindbox+2', 'Mystery Kids Blindbox - Image 2'),
(21, 11, 'https://via.placeholder.com/300?text=Gadget+Accessories+Blindbox+1', 'Gadget Accessories Blindbox - Image 1'),
(22, 11, 'https://via.placeholder.com/300?text=Gadget+Accessories+Blindbox+2', 'Gadget Accessories Blindbox - Image 2'),
(23, 12, 'https://via.placeholder.com/300?text=Limited+Edition+Art+Blindbox+1', 'Limited Edition Art Blindbox - Image 1'),
(24, 12, 'https://via.placeholder.com/300?text=Limited+Edition+Art+Blindbox+2', 'Limited Edition Art Blindbox - Image 2'),
(25, 13, 'https://via.placeholder.com/300?text=Mystery+Puzzle+Blindbox+1', 'Mystery Puzzle Blindbox - Image 1'),
(26, 13, 'https://via.placeholder.com/300?text=Mystery+Puzzle+Blindbox+2', 'Mystery Puzzle Blindbox - Image 2'),
(27, 14, 'https://via.placeholder.com/300?text=Surprise+Music+Blindbox+1', 'Surprise Music Blindbox - Image 1'),
(28, 14, 'https://via.placeholder.com/300?text=Surprise+Music+Blindbox+2', 'Surprise Music Blindbox - Image 2'),
(29, 15, 'https://via.placeholder.com/300?text=Surprise+Food+Blindbox+1', 'Surprise Food Blindbox - Image 1'),
(30, 15, 'https://via.placeholder.com/300?text=Surprise+Food+Blindbox+2', 'Surprise Food Blindbox - Image 2'),
(31, 16, 'https://via.placeholder.com/300?text=Luxury+Fashion+Blindbox+1', 'Luxury Fashion Blindbox - Image 1'),
(32, 16, 'https://via.placeholder.com/300?text=Luxury+Fashion+Blindbox+2', 'Luxury Fashion Blindbox - Image 2');



INSERT INTO discount (discount_id, code, percentage, valid_from, valid_until) VALUES
(1, 'DISCOUNT10', 10.00, '2025-03-01', '2025-03-31'),
(2, 'SUMMER20', 20.00, '2025-06-01', '2025-06-30');


INSERT INTO orders (order_id, user_id, gacha_type, discount_id, order_date, total_amount, status, payment_status, shipping_status) VALUES
(1, 2, 'Toy Gacha', 1, '2025-03-20', 100.00, 'Shipped', 'Paid', 'Delivered'),
(2, 3, 'Electronics Gacha', 2, '2025-03-21', 150.00, 'Processing', 'Pending', 'Pending');

INSERT INTO orderdetail (order_detail_id, order_id, product_id, quantity, price, blindbox_id) VALUES
(1, 1, 1, 2, 15.00, 1),
(2, 1, 5, 1, 22.50, 2),
(3, 2, 3, 2, 25.00, 3);

INSERT INTO payment (payment_id, order_id, user_id, amount, status, method, transaction_id) VALUES
(1, 1, 2, 100.00, 'Success', 'Credit Card', 'TXN123456'),
(2, 2, 3, 150.00, 'Pending', 'PayPal', 'TXN789101');



INSERT INTO shipment (shipment_id, order_id, address, method, status) VALUES
(1, 1, '123 John St.', 'Standard', 'Delivered'),
(2, 2, '789 Jane St.', 'Express', 'Shipped');


INSERT INTO review (review_id, product_id, blindbox_id, user_id, rating, review_text) VALUES
(1, 1, 1, 2, 4, 'Great toy, my kids loved it!'),
(2, 3, 3, 3, 5, 'Loved the surprise speaker, excellent quality!'),
(3, 5, 5, 1, 5, 'Super cute plush toy, highly recommend for collectors!');


INSERT INTO result (result_id, order_id, result_text, blindbox_id) VALUES
(1, 1, 'Congratulations, you won a rare toy!', 1),
(2, 2, 'You received a limited-edition gadget.', 3);

INSERT INTO wishlist (wishlist_id, user_id, product_id, blindbox_id) VALUES
(1, 2, 1, 1),
(2, 3, 2, 2);


-- Show all data from Role table
SELECT * FROM role;

-- Show all data from Category table
SELECT * FROM category;

-- Show all data from User table
SELECT * FROM user;

-- Show all data from Blindbox table
SELECT * FROM blindbox;

-- Show all data from Product table
SELECT * FROM product;

-- Show all data from Cart table
SELECT * FROM cart;

-- Show all data from Cartitem tableblindbox_image_id
SELECT * FROM cartitem;

-- Show all data from Discount table
SELECT * FROM discount;

-- Show all data from Orders table
SELECT * FROM orders;

-- Show all data from Orderdetail table
SELECT * FROM orderdetail;

-- Show all data from Payment table
SELECT * FROM payment;

-- Show all data from Shipment table
SELECT * FROM shipment;

-- Show all data from Review table
SELECT * FROM review;

-- Show all data from Result table
SELECT * FROM result;

-- Show all data from Wishlist table
SELECT * FROM wishlist;

