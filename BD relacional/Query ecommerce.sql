-- DROP database ecommerce;

-- Creación de la base de datos
CREATE DATABASE ecommerce;

-- Selección de la base de datos
USE ecommerce;

-- Creación de la tabla User
CREATE TABLE `user_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Creación de la tabla Product
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`)
) ;

-- Creación de la tabla Order
CREATE TABLE `order_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user-orderentity-fk` (`userid`),
  CONSTRAINT `user-orderentity-fk` FOREIGN KEY (`userid`) REFERENCES `user_entity` (`id`)
) ;

-- Creación de la tabla OrderItem
CREATE TABLE `orderitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `orderid` int(11) DEFAULT NULL,
  `productid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderitem-order-fk` (`orderid`),
  KEY `orderitem-product-fk` (`productid`),
  CONSTRAINT `orderitem-product-fk` FOREIGN KEY (`productid`) REFERENCES `product` (`id`),
  CONSTRAINT `orderitem-order-fk` FOREIGN KEY (`orderid`) REFERENCES `order_entity` (`id`)
  );