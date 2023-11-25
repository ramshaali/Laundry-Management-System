create database userdb;
use userdb;
CREATE TABLE info (
id INT(6) UNSIGNED  PRIMARY KEY,
firstname VARCHAR(30) NOT NULL,
lastname VARCHAR(30) NOT NULL

);

drop database LMS;
create database  LMS;
use LMS;


create table OrderCatalog(
id INT UNSIGNED  PRIMARY KEY
);

create table customerCatalog(
id INT UNSIGNED  PRIMARY KEY
);




create table laundryAdmin(
id INT UNSIGNED  PRIMARY KEY,
name VARCHAR(30) NOT NULL,
pass VARCHAR(30) NOT NULL,
ccat_id   INT(6) UNSIGNED NOT null,
ocat_id   INT(6) UNSIGNED NOT null,
FOREIGN KEY (ccat_id) REFERENCES customerCatalog(id),
FOREIGN KEY (ocat_id) REFERENCES OrderCatalog(id)

);


create table Customer(
id INT UNSIGNED  PRIMARY KEY,
name VARCHAR(30) NOT NULL,
pass VARCHAR(30) NOT NULL,
address VARCHAR(30) NOT NULL,
cnum  VARCHAR(30) NOT NULL,
cat_id  INT(6) UNSIGNED NOT null,
  FOREIGN KEY (cat_id) REFERENCES customerCatalog(id)

);


create table cust_Order(
id INT UNSIGNED  PRIMARY KEY,
cat_id   INT UNSIGNED NOT null,
cid   INT UNSIGNED NOT null,
 FOREIGN KEY (cid) REFERENCES Customer(id),
FOREIGN KEY (cat_id) REFERENCES OrderCatalog(id),

status VARCHAR(50) NOT NULL

);

create table OrderPayment(
id INT UNSIGNED  PRIMARY KEY,
oid   INT UNSIGNED NOT null,
FOREIGN KEY (oid) REFERENCES cust_Order(id),
T_price VARCHAR(50) NOT NULL,
type VARCHAR(50) NOT NULL

);

create table OrderFeedback(
id INT UNSIGNED  PRIMARY KEY,
oid   INT UNSIGNED NOT null,
FOREIGN KEY (oid) REFERENCES cust_Order(id),
comment VARCHAR(50) NOT NULL

);

create table Item(
id INT UNSIGNED ,
oid   INT UNSIGNED NOT null,
 FOREIGN KEY (oid) REFERENCES  cust_Order(id),
type VARCHAR(50) NOT NULL,
price VARCHAR(50) NOT NULL,
 PRIMARY KEY(id,oid)

);


create table rider(
id INT UNSIGNED  PRIMARY KEY,
name VARCHAR(30) NOT NULL,
pass VARCHAR(30) NOT NULL,
cnum  VARCHAR(30) NOT NULL


);

create table delivery(
id INT UNSIGNED  PRIMARY KEY,
o_id   INT UNSIGNED NOT null,
r_id   INT UNSIGNED NOT null,
FOREIGN KEY (o_id) REFERENCES  cust_Order(id),
FOREIGN KEY (r_id) REFERENCES rider(id)

);

INSERT INTO OrderCatalog (id) VALUES (101);
INSERT INTO customerCatalog (id) VALUES (102);



INSERT INTO Customer(id, name, pass,address,cnum,cat_id) VALUES (1,"ramsha","rm","rwp","03000000", 102);
INSERT INTO laundryAdmin(id, name, pass,ccat_id,ocat_id) VALUES (1,"sabeen","us", 102, 101);

select * from laundryAdmin;

delete from laundryAdmin
where id=0;


delete from Item
where oid=2;

delete from OrderPayment
where oid=2;

delete from cust_Order
where id=2;

select * from cust_Order;
select * from  OrderPayment where oid=2



