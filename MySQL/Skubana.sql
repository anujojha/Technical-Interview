CREATE DATABASE skubana; 

CREATE TABLE companies(

    company_id                  INT     NOT NULL PRIMARY KEY, 
    company_name                VARCHAR(50)         NOT NULL, 
    contact_name                VARCHAR(50)         NOT NULL, 
    contact_phone               VARCHAR(30)         NOT NULL, 
    contact_email               VARCHAR(50)         NOT NULL, 
    created                     DATETIME            NOT NULL 
);


CREATE TABLE customers (

    customer_id                 INT  NOT NULL PRIMARY KEY, 
    company_id                  INT  NOT NULL, 
    customer_name               VARCHAR(100)  NOT NULL, 
    customer_company            VARCHAR(100), 
    CONSTRAINT customers_fk_company 
        FOREIGN KEY (company_id) REFERENCES companies (company_id) 
); 


CREATE TABLE orders (
 
    order_id                    INT NOT NULL PRIMARY KEY, 
    company_id                  INT  NOT NULL, 
    customer_id                 INT NOT NULL, 
    order_number                VARCHAR(200), 
    order_date                  DATETIME NOT NULL, 
    created                     DATETIME NOT NULL, 
    order_status                INT NOT NULL, 

    CONSTRAINT orders_fk_company 
        FOREIGN KEY (company_id) REFERENCES companies (company_id), 

    CONSTRAINT orders_fk_customer 
        FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
); 


CREATE TABLE order_items 
( 
    order_item_id               INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    order_id                    INT NOT NULL, 
    listing_sku                 VARCHAR(100), 
    listing_name                VARCHAR(200), 
    quantity_ordered            INT                 NOT NULL, 
    quantity_shipped            INT                 NOT NULL, 
    unit_price                  DECIMAL(10,2)       NOT NULL, 
    tax                         DECIMAL(10,2)       NOT NULL, 
    CONSTRAINT order_items_fk_order 
        FOREIGN KEY (order_id) REFERENCES orders (order_id) 
); 



INSERT INTO companies 
    (company_id, company_name, contact_name, contact_phone, contact_email,  created) 
VALUES 
    (1, 'Test Company', 'Johnny Ceo', '404-555-5555', 'johnny@testcompany.com', '2014-01-01 08:00:00'); 

INSERT INTO customers 
(customer_id, company_id, customer_name, customer_company) 
VALUES 
    (1, 1, 'Adam Aaronson', null), 
    (2, 1, 'Billy Blanks', null), 
    (3, 1, 'Cri Crow', 'Crow Industries'), 
    (4, 1, 'Doug Dorfman', 'Fake Company, LLC'), 
    (5, 1, 'Edward Edmonton', null), 
    (6, 1, 'Francis Friedman', null); 


INSERT INTO orders 
(order_id, company_id, customer_id, order_number, order_date, created, order_status) 
VALUES 
(100, 1, 1, 'TESTORDER123', '2014-09-01 09:30:00', '2014-09-01 09:30:00', 1),
(101, 1, 2, 'TESTORDER124', '2014-09-01 15:20:00', '2014-09-01 15:20:00', 2),
(102, 1, 2, 'TESTORDER125', '2014-09-01 09:30:00', '2014-09-01 09:30:00', 2),
(103, 1, 3, 'TESTORDER200', '2014-09-01 23:59:59', '2014-09-01 23:59:59', 1),
(104, 1, 3, 'TESTORDER300', '2014-09-02 00:00:00', '2014-09-02 00:00:00', 1) 
; 

INSERT INTO order_items 
(order_id, listing_sku, listing_name, quantity_ordered, quantity_shipped, unit_price, tax) 
VALUES 
(100, 'CWBB-1', 'Cowboy Boots (Red)', 3, 3, 100, .75),
(101, 'CWBB-1', 'Cowboy Boots (Red)', 1, 0, 30, .25),
(101, 'LACE-2', 'Shoe laces', 1, 0, 3, .10),
(102, 'PUMP-00', 'Yellow Pumps', 1, 1, 40, 4.27),
(103, 'PUMP-01', 'Red Pumps', 1, 0, 9, 1),
(104, 'PUMP-00', 'Yellow Pumps', 1, 0, 80, 5) 
;